package TextEditorJB.Components

import TextEditorJB.TextColoringService.TextColoringService
import java.awt.Color
import java.awt.Font
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.geom.Rectangle2D
import javax.swing.JPanel


class TextPanel : JPanel() {

    var fullText : Array<String> =  arrayOf("")
    var activeRow = 0
    var rowY = 35
    var lineSpacing = 20
    var textFont = Font("Calibri",0,20)
    var caret : Caret = getCaret() as Caret
    var drawingSelection = false //статус выделения, пока сбрасывается только мышкой
    var selectingStart = 0
    var selectingEnd = 0
    var insertFlag = false

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

        for ((index, value) in fullText[activeRow].withIndex()) {

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

        // вывод строки g.drawString(textRow,20,rowY)
        var coordY = 35
        var service = TextColoringService()
        for (string in fullText){
            g.drawString(string,20,coordY)
            //service.paintKeyWords(string,this,20,coordY)
            coordY+=lineSpacing
        }

        //service.paintKeyWords(fullText[activeRow],g,20,coordY)
        //coordY+=lineSpacing

        caret.paint(g)
    }

    companion object{
        //var textRow : String = "<html><font color=\"blue\">text text</font></html>"
        var buffer = ""
    }
}