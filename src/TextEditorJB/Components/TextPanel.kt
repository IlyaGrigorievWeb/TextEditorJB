package TextEditorJB.Components

import java.awt.Color
import java.awt.Font
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.geom.Rectangle2D
import javax.swing.JComponent
import javax.swing.JPanel

class TextPanel : JPanel() {

    var caret : Caret = getCaret() as Caret

    private fun getCaret() : Any{
        val caret = Caret()
        caret.isVisible = true
        return caret
    }

    override fun paintComponent(g: Graphics?)  {
        super.paintComponent(g)
        //var myFont:Font = Font(Font.SERIF,Font.BOLD,20)        УЗНАТЬ РАЗМЕРЫ БУКВ В ШИРИНУ И ВЫСОТУ И ДЕЛИТЬ РАССТОЯНИЕ ОТ МЫШИ ДО НАЧАЛА ПЛЙНА И ДЕЛИТЬ НА ЦЕЛО РАССТОЯНИЕ ОТ МЫШИ ПО ГОРИЗОНТАЛИ НА ШИРИНУ И ПОЛУЧАТЬ СИМВОЛ
        //var myGraph = g as Graphics2D                          МАССИВ СТРОК ПО СТРОКЕ нА КАЖДУЮ РЕАЛЬНУЮ СТРОКУ, НАЧИНАТЬ ЕЕ С НОВОЙ КООРДИНАТЫ В drawString, СМЕЩЕНИЕ НУЖНО ТОЛЬКО В ОТРИСОВКИ
        //myGraph.font = myFont                                  КАРЕТКУ МОЖНО РИСОВАТЬ ПОВЕРХ СИИВОЛОВ НО С ОТСТУПОМ ПО ШИРИНЕ И ВЫСОТЕ
        //myGraph.drawString(strText,30,30)                      ПОГУГЛИТЬ ПРО СТИЛИЗАЦИЮ ИЗ HTML В SWING
        //ПРЯМОУГОЛЬНИКИ КОТОРЫЕ ОТРИСОВЫВАЮТСЯ ПЕРВЫМИ ИЗ ШИРИНЫ И ВЫСОТЫ СИМВОЛОВ И ЭТО БУДЕТ ВЫДЕЛЕНИЕ
        //ЗАВЕСТИ МАПУ НА СИМВОЛЫ


        var rec = Rectangle2D.Double(20.0,20.0,40.0,20.0) //4 символа 20го CALIBRI
        (g as Graphics2D).paint = Color.GREEN
        (g as Graphics2D).fill(rec)

        (g as Graphics2D).paint = Color.BLACK
        var myFont: Font = Font("Calibri",0,20)
        (g as Graphics).font = myFont

        var text = strText

        (g as Graphics).drawString(strText,20,35)

        caret.paint(g)
    }

    companion object{
        var strText : String = ""
    }
}

class Caret : JComponent(){
    var positionX = 20;
    var positionY = 35;
    val charCaret = "|"
    var leftWidth = 0;
    var rightWidth = 0;
    var positionInRow = 0;

    fun setXpositionByMouse(string: String,mousePositionX : Int) : Int{

        var positionX = 0
        for (ch in string)
        {
            positionX += characterWidthMap[ch.toString()]!!
            if (positionX>=mousePositionX)
                return positionX
        }
        return positionX
    }

    fun left(){
        if (leftWidth>0)
            positionX -= leftWidth
    }

    override fun paintComponent(g: Graphics?) {
        //super.paintComponent(g)
        var g2 = g as Graphics2D;
        var myFont:Font = Font("Calibri",0,20)
        (g as Graphics).font = myFont
        g2.drawString(charCaret,positionX,positionY)
    }
    companion object {
        val lineInterval = 3
        val characterWidthMap: Map<String, Int> = mapOf(
            "q" to 4, "w" to 10, "e" to 7, "r" to 6 ,
            "t" to 4, "y" to 6, "u" to 6, "i" to 2,
            "o" to 6, "p" to 6, "a" to 5, "s" to 4,
            "d" to 6, "f" to 4, "g" to 6, "h" to 6,
            "j" to 2, "k" to 4, "l" to 2, "z" to 4,
            "x" to 4, "c" to 4, "v" to 6, "b" to 6,
            "n" to 6, "m" to 12, " " to 3
        )
    }
}