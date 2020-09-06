package TextEditorJB.Components

import TextEditorJB.Entities.SourceText
import TextEditorJB.Services.NavigationService
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.geom.Rectangle2D
import javax.swing.JComponent

class TextSelection ( textPanel: TextPanel,sourceText: SourceText,navigationService: NavigationService) : JComponent() {

    val sourceText = sourceText
    val panel = textPanel
    var selectingStartRow = 0
    var selectingEndRow = 0
    var selectingStartChar = 0
    var selectingEndChar = 0
    var buffer = ""
    var navigationService =   navigationService
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
        drawingSelection = false;
        buffer = ""
    }

//    override fun paint(g: Graphics?) {
//        var a = 10
//        if (drawingSelection)
//        {
//            var g2 = g as Graphics2D;
//            var rectangles = arrayOf(Rectangle2D.Double())
//            val metrics = getFontMetrics(panel.textFont)
//
//            when (selectingEndRow - selectingStartRow)
//            {
//                0 -> {
//                    var x = 20 + metrics.stringWidth(panel.fullText[selectingStartRow].substring(0,selectingStartChar))
//                    var y = panel.rowY - 14// panel.lineSpacing * selectingStartRow-1 //т.к. 35 это низ уже 1 строки
//                    var height = metrics.height
//                    var width = metrics.stringWidth(panel.fullText[selectingStartRow].substring(selectingStartChar-1,selectingEndChar-1))
//                    rectangles = arrayOf(Rectangle2D.Double(x.toDouble(),y.toDouble(),width.toDouble(),height.toDouble()))
//                }
//                1 -> rectangles = arrayOf(Rectangle2D.Double(),Rectangle2D.Double())
//                2 -> rectangles = arrayOf(Rectangle2D.Double(),Rectangle2D.Double(),Rectangle2D.Double())
//            }
//
//            for (i in 0..selectingEndRow - selectingStartRow)
//            {
//
//            }
////            for ((index, value) in fullText[activeRow].withIndex()) {
////
////                if (index < textSelection.selectingStart){
////                    x += metrics.charWidth(value)
////                }
////                else if (index in textSelection.selectingStart..textSelection.selectingEnd)
////                {
////                    w += metrics.charWidth(value)
////                }
////            }
//            //var rec = Rectangle2D.Double( x,20.0,w,20.0)
//
//            g2.paint = Color.YELLOW
//            for (rec in rectangles)
//                g2.fill(rec)
//            g2.paint = Color.BLACK
//        }
//    }
    override fun paintComponent(g: Graphics?) { //TODO: Работает только слева на право, сделать что бы работало в обе стороны
        if (drawingSelection)
        {
            var g2 = g as Graphics2D;
            var rectangles = arrayOf<Rectangle2D>()
            val metrics = getFontMetrics(panel.textFont)

            when (selectingEndRow - selectingStartRow)
            {
                0 -> {
                var x = panel.borderX + 4 + metrics.stringWidth(sourceText.text[selectingStartRow].substring(0,selectingStartChar))
                var y = 35 + panel.lineSpacing * selectingStartRow - metrics.height + 10//т.к. 35 это низ уже 1 строки
                var height = metrics.height - 2
                var width = metrics.stringWidth(sourceText.text[selectingStartRow].substring(selectingStartChar,selectingEndChar))
                rectangles = arrayOf(Rectangle2D.Double(x.toDouble(),y.toDouble(),width.toDouble(),height.toDouble()))
                }
                1 -> {
                    var x = panel.borderX + 4 + metrics.stringWidth(sourceText.text[selectingStartRow].substring(0,selectingStartChar))
                    var y = 35 + panel.lineSpacing * selectingStartRow - metrics.height + 10//т.к. 35 это низ уже 1 строки
                    var height = metrics.height - 2
                    var width = panel.size.width
                    val firstRectangle =  Rectangle2D.Double(x.toDouble(),y.toDouble(),width.toDouble(),height.toDouble())
                    x = 0
                    y = 35 + panel.lineSpacing * selectingEndRow - metrics.height + 10//т.к. 35 это низ уже 1 строки
                    height = metrics.height - 2
                    width = metrics.stringWidth(sourceText.text[selectingEndRow].substring(0,selectingEndChar))+20
                    val secondRectangle =  Rectangle2D.Double(x.toDouble(),y.toDouble(),width.toDouble(),height.toDouble())
                    rectangles = arrayOf(firstRectangle,secondRectangle)
                }
                else -> {
                    var x = panel.borderX + 4 + metrics.stringWidth(sourceText.text[selectingStartRow].substring(0,selectingStartChar))
                    var y = 35 + panel.lineSpacing * selectingStartRow - metrics.height + 10//т.к. 35 это низ уже 1 строки
                    var height = metrics.height - 2
                    var width = panel.size.width
                    val firstRectangle =  Rectangle2D.Double(x.toDouble(),y.toDouble(),width.toDouble(),height.toDouble())
                    x = 0
                    y = 35 + panel.lineSpacing * (selectingStartRow+1) - metrics.height + 10
                    height = panel.lineSpacing * (selectingEndRow - selectingStartRow -1)
                    width = panel.size.width
                    val secondRectangle =  Rectangle2D.Double(x.toDouble(),y.toDouble(),width.toDouble(),height.toDouble())
                    x = 0
                    y = 35 + panel.lineSpacing * selectingEndRow - metrics.height + 10//т.к. 35 это низ уже 1 строки
                    height = metrics.height - 2
                    width = metrics.stringWidth(sourceText.text[selectingEndRow].substring(0,selectingEndChar))+20
                    val thirdRectangle =  Rectangle2D.Double(x.toDouble(),y.toDouble(),width.toDouble(),height.toDouble())
                    rectangles = arrayOf(firstRectangle,secondRectangle,thirdRectangle)
                }
            }

            g2.paint = Color.YELLOW
            for (rec in rectangles)
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
        navigationService.Right()
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
            buffer = StringBuilder(buffer).insert(0,sourceText.text[sourceText.activeRow][caretIndex]).toString();
        println(buffer)


        //panel.repaint()
    }
    fun selectUp() // если уже было выделение смещаем старт индекс влево если нет ставим енд индекс там где мы сейчас и двигаем старт индекс на 1 TODO:Переписать методы под сет по каретке, что бы не считаь позицию самому, брать значения до и после смекщенияч
    //лучше завязаться на конечной каретке
    {
        if (drawingSelection){

            navigationService.Up()
            setEndState()
        }
        else {
            drawingSelection = true
            setBeginState()
            navigationService.Up()
            setEndState()
        }

        println(buffer)

        //panel.repaint()
    }
    fun selectDown()
    {
        if (drawingSelection){

            navigationService.Down()
            setEndState()
        }
        else {
            drawingSelection = true
            setBeginState()
            navigationService.Down()
            setEndState()
        }

        println(buffer)

        //panel.repaint()
    }

    fun selectByClick(mouseX : Int, mouseY : Int)
    {
//        if (drawingSelection)
//        {
//            panel.caret.setPositionByMouseCoord(mouseX,mouseY)
//
//            selectingEndRow = panel.activeRow
//            selectingEndChar = panel.caret.positionInRow
//
//            updateBuffer()
//        }
//        else{
//            drawingSelection = true
//
//            selectingStartRow = panel.activeRow
//            selectingStartChar = panel.caret.positionInRow
//
//            panel.caret.setPositionByMouseCoord(mouseX,mouseY)
//
//            selectingEndRow = panel.activeRow
//            selectingEndChar = panel.caret.positionInRow
//
//            updateBuffer()
//        }
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

            updateBuffer()
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

    fun updateBuffer(){ //TODO поправить баг с отрисовкой и буфером

//        var resultString = ""
//        var selectedTextArray = panel.fullText.copyOfRange(selectingStartRow,selectingEndRow+1)
//
//
//        for ((index,string) in selectedTextArray.withIndex())
//        {
//            if (index == 0)
//            {
//                resultString += selectedTextArray[index].substring(selectingStartChar,selectedTextArray[index].lastIndex)
//            }
//            else if(index == selectedTextArray.lastIndex){
//                resultString += selectedTextArray[index].substring(0,selectingStartChar)
//            }
//            else{
//                resultString += selectedTextArray[index]
//            }
//            resultString += "\n"
//        }
//        buffer = resultString
//        print(buffer)
    }
}