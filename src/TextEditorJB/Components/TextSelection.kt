package TextEditorJB.Components

import TextEditorJB.Entities.SourceText
import TextEditorJB.Services.NavigationService
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.geom.Rectangle2D
import javax.swing.JComponent

class TextSelection (private val panel: TextPanel, private val sourceText: SourceText,private var navigationService: NavigationService) : JComponent() {

    var selectingStartRow = 0
    var selectingEndRow = 0
    var selectingStartChar = 0
    var selectingEndChar = 0
    var buffer = ""
    var drawingSelection = false

    set(value){
        if (!value) {
            selectingStartChar = 0
            selectingEndChar = 0
            buffer = ""
        }
        field = value

    }

    fun resetBuffer(){
        drawingSelection = false
        buffer = ""
    }

    override fun paintComponent(g: Graphics?) { //TODO: Работает только слева на право, сделать что бы работало в обе стороны
        if (this.drawingSelection)
        {
            val g2 = g as Graphics2D
            val rectangles : Array<Rectangle2D>
            val metrics = getFontMetrics(panel.textFont)

            when (selectingEndRow - selectingStartRow)
            {
                0 -> {
                    val x = panel.borderX + 4 + metrics.stringWidth(sourceText.text[selectingStartRow].substring(0,selectingStartChar))
                    val y = panel.borderY + panel.lineSpacing * (selectingStartRow - panel.workspaceService.position) - metrics.height + 10//т.к. 35 это низ уже 1 строки
                    val height = metrics.height - 2
                    val width = metrics.stringWidth(sourceText.text[selectingStartRow].substring(selectingStartChar,selectingEndChar))
                rectangles = arrayOf(Rectangle2D.Double(x.toDouble(),y.toDouble(),width.toDouble(),height.toDouble()))
                }
                1 -> {
                    var x = panel.borderX + 4 + metrics.stringWidth(sourceText.text[selectingStartRow].substring(0,selectingStartChar))
                    var y = panel.borderY + panel.lineSpacing * (selectingStartRow - panel.workspaceService.position) - metrics.height + 10//т.к. 35 это низ уже 1 строки
                    var height = metrics.height - 2
                    var width = panel.size.width
                    val firstRectangle =  Rectangle2D.Double(x.toDouble(),y.toDouble(),width.toDouble(),height.toDouble())
                    x = 0 + panel.borderX + 4
                    y = panel.borderY + panel.lineSpacing * (selectingEndRow - panel.workspaceService.position) - metrics.height + 10//т.к. 35 это низ уже 1 строки
                    height = metrics.height - 2
                    width = metrics.stringWidth(sourceText.text[selectingEndRow].substring(0,selectingEndChar))
                    val secondRectangle =  Rectangle2D.Double(x.toDouble(),y.toDouble(),width.toDouble(),height.toDouble())
                    rectangles = arrayOf(firstRectangle,secondRectangle)
                }
                else -> {
                    var x = panel.borderX + 4 + metrics.stringWidth(sourceText.text[selectingStartRow].substring(0,selectingStartChar))
                    var y = panel.borderY + panel.lineSpacing * (selectingStartRow - panel.workspaceService.position) - metrics.height + 10//т.к. 35 это низ уже 1 строки
                    var height = metrics.height - 2
                    var width = panel.size.width
                    val firstRectangle =  Rectangle2D.Double(x.toDouble(),y.toDouble(),width.toDouble(),height.toDouble())
//                    x = 0 + panel.borderX + 4
//                    y = 35 + panel.lineSpacing * (selectingStartRow+1 - panel.workspaceService.position) - metrics.height + 10
//                    height = panel.lineSpacing * (selectingEndRow - selectingStartRow -1)
//                    width = panel.size.width
//                    val secondRectangle =  Rectangle2D.Double(x.toDouble(),y.toDouble(),width.toDouble(),height.toDouble())
                    x = 0 + panel.borderX + 4
                    y = panel.borderY + panel.lineSpacing * (selectingStartRow+1 - panel.workspaceService.position) - metrics.height + 10
                    height = panel.lineSpacing * (selectingEndRow - selectingStartRow -1)
                    width = panel.size.width
                    val secondRectangle =  Rectangle2D.Double(x.toDouble(),y.toDouble(),width.toDouble(),height.toDouble())
                    x = 0 + panel.borderX + 4
                    y = panel.borderY + panel.lineSpacing * (selectingEndRow - panel.workspaceService.position) - metrics.height + 10//т.к. 35 это низ уже 1 строки
                    height = metrics.height - 2
                    width = metrics.stringWidth(sourceText.text[selectingEndRow].substring(0,selectingEndChar))
                    val thirdRectangle =  Rectangle2D.Double(x.toDouble(),y.toDouble(),width.toDouble(),height.toDouble())
                    rectangles = arrayOf(firstRectangle,secondRectangle,thirdRectangle)
                }
            }

            g2.paint = Color.YELLOW
            for (rec in rectangles)
                if (rec.y > 0.toDouble())
                    g2.fill(rec)
            g2.paint = Color.BLACK
        }
    }
    fun setBeginState()
    {
        selectingStartRow = sourceText.activeRow
        selectingStartChar = sourceText.positionInRow
    }
    fun setEndState()
    {
        selectingEndRow = sourceText.activeRow
        selectingEndChar = sourceText.positionInRow
    }
    fun setBeginState(row : Int, position : Int)
    {
        selectingStartRow = row
        selectingStartChar = position
    }
    fun setEndState(row : Int, position : Int)
    {
        selectingEndRow = row
        selectingEndChar = position
    }
    fun selectLeft(func : () -> Unit) // если уже было выделение смещаем старт индекс влево если нет ставим енд индекс там где мы сейчас и двигаем старт индекс на 1 TODO:Переписать методы под сет по каретке, что бы не считаь позицию самому, брать значения до и после смекщенияч
    //лучше завязаться на конечной каретке
    {

//        panel.caret.moveLeft()
//        val caretIndex = panel.caret.positionInRow
//
//        if (drawingSelection){
//            if (caretIndex == selectingEndChar)
//                drawingSelection = false
//            else{
//                if(panel.caret.positionInRow == selectingStartRow) //TODO Решение бага со сторонами - проверять если каретка равная позиции в строке то это направление выделения
//                {
//                    selectingStartChar = caretIndex - 1
//                }
//                else
//                    selectingStartChar = caretIndex
//            }
//        }
//        else{
//            drawingSelection = true
//            selectingStartChar  = caretIndex
//            selectingEndChar = caretIndex + 1
//        }
//
//        if (drawingSelection)
//            buffer = StringBuilder(buffer).insert(0,panel.fullText[panel.activeRow][caretIndex]).toString();
//        println(buffer)
//
//        panel.repaint()


    }
    fun selectRight()
    {
        navigationService.right()
        val caretIndex = sourceText.positionInRow

        if (drawingSelection){
            if (caretIndex == selectingStartChar || selectingEndChar == selectingStartChar)
                drawingSelection = false
            else
                selectingEndChar = caretIndex
        }
        else{
            drawingSelection = true
            selectingStartChar  = caretIndex - 1
            selectingEndChar = caretIndex
        }

        if (drawingSelection)
            buffer = StringBuilder(buffer).insert(0,sourceText.text[sourceText.activeRow][caretIndex]).toString()
        println(buffer)
    }
    fun selectUp() // если уже было выделение смещаем старт индекс влево если нет ставим енд индекс там где мы сейчас и двигаем старт индекс на 1 TODO:Переписать методы под сет по каретке, что бы не считаь позицию самому, брать значения до и после смекщенияч
    //лучше завязаться на конечной каретке
    {
        if (drawingSelection){

            navigationService.up()
            setEndState()
        }
        else {
            drawingSelection = true
            setBeginState()
            navigationService.up()
            setEndState()
        }

        println(buffer)
    }
    fun selectDown()
    {
        if (drawingSelection){

            navigationService.down()
            setEndState()
        }
        else {
            drawingSelection = true
            setBeginState()
            navigationService.down()
            setEndState()
        }

        println(buffer)
    }

    fun selectMouseMotion(mouseX : Int, mouseY : Int,selected : Boolean){
        if (!drawingSelection && selected)
        {
            drawingSelection = true

            selectingStartRow = sourceText.activeRow
            selectingStartChar = sourceText.positionInRow

            panel.caret.setPositionByMouseCoord(mouseX,mouseY)

            selectingEndRow = sourceText.activeRow
            selectingEndChar = sourceText.positionInRow

            //updateBuffer()
        }
        else if (drawingSelection && !selected){
            panel.caret.setPositionByMouseCoord(mouseX,mouseY)

            selectingEndRow = sourceText.activeRow
            selectingEndChar = sourceText.positionInRow
        }
        else if (drawingSelection && selected){
            panel.caret.setPositionByMouseCoord(mouseX,mouseY)

            selectingEndRow = sourceText.activeRow
            selectingEndChar = sourceText.positionInRow
        }
    }
}
