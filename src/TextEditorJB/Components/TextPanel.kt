package TextEditorJB.Components

import java.awt.Color
import java.awt.Font
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.geom.Rectangle2D
import javax.swing.JComponent
import javax.swing.JPanel


class TextPanel : JPanel() {

    var textFont = Font("Calibri",0,20)
    var caret : Caret = getCaret() as Caret
    var drawingSelection = false //статус выделения, пока сбрасывается только мышкой
    var selectingStart = 0
    var selectingEnd = 0

    private fun getCaret() : Any{
        val caret = Caret(this)
        caret.isVisible = true
        return caret
    }


    fun drawSelection(g: Graphics?){
        var g2 = g as Graphics2D;
        var x = 20.0
        var w = 0.0
        val metrics = getFontMetrics(textFont)

        for ((index, value) in TextPanel.textRow.withIndex()) {

            if (index < selectingStart){
                x += metrics.charWidth(value)
            }
            else if (index in selectingStart..selectingEnd)
            {
                w += metrics.charWidth(value)
            }
        }

        var rec = Rectangle2D.Double( x,20.0,w,20.0)

        g2.paint = Color.GREEN
        g2.fill(rec)
        g2.paint = Color.BLACK
    }

    override fun paintComponent(g: Graphics?)  {
        super.paintComponent(g)
        //var myFont:Font = Font(Font.SERIF,Font.BOLD,20)
        //var myGraph = g as Graphics2D                          МАССИВ СТРОК ПО СТРОКЕ нА КАЖДУЮ РЕАЛЬНУЮ СТРОКУ, НАЧИНАТЬ ЕЕ С НОВОЙ КООРДИНАТЫ В drawString, СМЕЩЕНИЕ НУЖНО ТОЛЬКО В ОТРИСОВКИ
        //myGraph.font = myFont                                  КАРЕТКУ МОЖНО РИСОВАТЬ ПОВЕРХ СИИВОЛОВ НО С ОТСТУПОМ ПО ШИРИНЕ И ВЫСОТЕ
        //myGraph.drawString(textRow,30,30)                      ПОГУГЛИТЬ ПРО СТИЛИЗАЦИЮ ИЗ HTML В SWING
                                                             //ПРЯМОУГОЛЬНИКИ КОТОРЫЕ ОТРИСОВЫВАЮТСЯ ПЕРВЫМИ ИЗ ШИРИНЫ И ВЫСОТЫ СИМВОЛОВ И ЭТО БУДЕТ ВЫДЕЛЕНИЕ

        if (this.drawingSelection) //выделение
        {
            drawSelection(g)
        }
        else{
            buffer = ""
        }


        (g as Graphics).font = textFont

        var text = textRow

        (g as Graphics).drawString(textRow,20,35)

        caret.paint(g)
    }

    companion object{
        var textRow : String = ""
        //var textRow : String = "<html><font color=\"blue\">text text</font></html>"
        var fullText : List<String> =  listOf("")
        var buffer = ""
    }
}

class Caret( textPanel: TextPanel) : JComponent(){

    var textPanel = textPanel
    var positionX = 16
    var positionY = 35
    val charCaret = "|"
    var positionInRow = 0;

    fun setXpositionByMouse(string: String,mousePositionX : Int) : Int{ //TODO: Не точно падает мышка, думаю дело в курсоре
        textPanel.drawingSelection = false;
        var positionX = 16
        val metrics = textPanel.getFontMetrics(textPanel.textFont)
        for (ch in string)
        {
            if (positionX>mousePositionX)
            {  return positionX}
            positionX += metrics.stringWidth(ch.toString())
        }
        return positionX
    }

    override fun paintComponent(g: Graphics?) {
        //super.paintComponent(g)
        var g2 = g as Graphics2D;

        var myFont:Font = Font("Calibri",0,20)
        (g as Graphics).font = myFont

        g2.drawString(charCaret,positionX,positionY)
    }

    fun moveLeft() { //TODO: Люфт влево-право +-1
        if (textPanel.caret.positionInRow > 0) {

            val char = TextPanel.textRow[textPanel.caret.positionInRow - 1].toString()
            val metrics = textPanel.getFontMetrics(textPanel.textFont)
            val width = metrics.stringWidth(char)

            textPanel.caret.positionX -= width
            textPanel.caret.positionInRow--
        }
    }
    fun moveRight() { //TODO: Люфт влево-право +-1
        if ( textPanel.caret.positionInRow < TextPanel.textRow.length) {

            val char = TextPanel.textRow[textPanel.caret.positionInRow - 1].toString()
            val metrics = textPanel.getFontMetrics(textPanel.textFont)
            val width = metrics.stringWidth(char)

            textPanel.caret.positionX += width
            textPanel.caret.positionInRow++

            textPanel.paint(textPanel.graphics)
        }
    }
    companion object {
//        val lineInterval = 3
//        val characterWidthMap: Map<String, Int> = mapOf(
//            "q" to 4, "w" to 10, "e" to 7 /*хорошо*/, "r" to 6 ,
//            "t" to 4 /*хорошо*/, "y" to 6, "u" to 6, "i" to 2,
//            "o" to 6, "p" to 6, "a" to 5, "s" to 4,
//            "d" to 6, "f" to 4, "g" to 6, "h" to 6,
//            "j" to 2, "k" to 4, "l" to 2, "z" to 4,
//            "x" to 4, "c" to 4, "v" to 6, "b" to 6,
//            "n" to 6, "m" to 12, " " to 3
//        )
    }
}