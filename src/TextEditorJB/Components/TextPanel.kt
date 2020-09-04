package TextEditorJB.Components

import TextEditorJB.Services.TextSelectionService
import TextEditorJB.TextColoringService.TextColoringService
import org.w3c.dom.Text
import java.awt.*
import java.awt.geom.Rectangle2D
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import javax.swing.JComponent
import javax.swing.JPanel


class TextPanel : JPanel() {

    var fullText : Array<String> =  arrayOf("")
    var activeRow = 0
    var rowY = 35
    var lineSpacing = 20
    var borderX = 16
    var textFont = Font("Calibri",0,20)
    var caret = getCaret() as Caret
    var textSelection = getS() as TextSelection//TextSelection(this)
    var service = TextColoringService(this)

    val rowsInWorkspace : Int
        get() {
            return this.size.height / lineSpacing
        }


    private fun getCaret() : Any{
        val caret = Caret(this)
        caret.isVisible = true
        return caret
    }
    private fun getS() : Any{
        val caret = TextSelection(this)
        caret.isVisible = true
        return caret
    }

    override fun paintComponent(g: Graphics?)  {
        var g2 = g as Graphics2D
        super.paintComponent(g)
        //var myFont:Font = Font(Font.SERIF,Font.BOLD,20)
        //var myGraph = g as Graphics2D                          МАССИВ СТРОК ПО СТРОКЕ нА КАЖДУЮ РЕАЛЬНУЮ СТРОКУ, НАЧИНАТЬ ЕЕ С НОВОЙ КООРДИНАТЫ В drawString, СМЕЩЕНИЕ НУЖНО ТОЛЬКО В ОТРИСОВКИ
        //myGraph.font = myFont                                  КАРЕТКУ МОЖНО РИСОВАТЬ ПОВЕРХ СИИВОЛОВ НО С ОТСТУПОМ ПО ШИРИНЕ И ВЫСОТЕ
        //myGraph.drawString(textRow,30,30)                      ПОГУГЛИТЬ ПРО СТИЛИЗАЦИЮ ИЗ HTML В SWING
        //ПРЯМОУГОЛЬНИКИ КОТОРЫЕ ОТРИСОВЫВАЮТСЯ ПЕРВЫМИ ИЗ ШИРИНЫ И ВЫСОТЫ СИМВОЛОВ И ЭТО БУДЕТ ВЫДЕЛЕНИЕ
        textSelection.paint(g)
//        if(fullText[activeRow].length > 2 && caret.positionInRow > 0) {
//            if (fullText[activeRow][caret.positionInRow-1] == '{' || fullText[activeRow][caret.positionInRow-1] == '}') {
//
//                if (service.stacks.getBracket(activeRow, caret.positionInRow - 1) != null) {
//                    println(service.stacks.getBracket(activeRow, caret.positionInRow - 1)!!.bracket)
//                    println(service.stacks.getBracket(activeRow, caret.positionInRow - 1)!!.row)
//                    println(service.stacks.getBracket(activeRow, caret.positionInRow - 1)!!.position)
//                }
//            }
//        }


        (g as Graphics).font = textFont
        if (caret.positionInRow > 1)
            service.paintBrackets(activeRow,caret.positionInRow,fullText[activeRow][caret.positionInRow-1],g)
        //(g as Graphics).TextRenderingHint = System.Drawing.Text.TextRenderingHint.AntiAlias;
        //(g as Graphics2D).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT)

        // вывод строки g.drawString(textRow,20,rowY)
        var coordY = 35

        for (string in fullText){
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON)
            g.drawString(string,20,coordY)
            service.paintKeyWords(string,g,20,coordY)
            //service.paintKeyWords(string,this,20,coordY)
            coordY+=lineSpacing
        }

        //coordY+=lineSpacing
        caret.paint(g)
    }

    companion object{ // TODO Баг с выделением , надо поиграться и в обратку через строку не выделяет

    }
}