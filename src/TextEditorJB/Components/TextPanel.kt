package TextEditorJB.Components

import TextEditorJB.Entities.SourceText
import TextEditorJB.Services.NavigationService
import TextEditorJB.Services.WorkspaceService
import TextEditorJB.TextColorer.TextColorerService
import java.awt.*
import javax.swing.JPanel


class TextPanel (sourceText: SourceText) : JPanel() {

    var workspaceText =  arrayOf("")

    var rowY = 35
    var lineSpacing = 20
    var borderX = 16
    var textFont = Font("Calibri",0,20)
    var caret = Caret(this,sourceText)
    val navigationService = NavigationService(this,sourceText)
    var textSelection = TextSelection(this,sourceText, navigationService)
    var coloringService = TextColorerService(this)
    val sourceText = sourceText
    val workspaceService = WorkspaceService(this, sourceText)

    val rowsInWorkspace : Int
        get() {
            return this.size.height / lineSpacing
        }


//    private fun getCaret() : Any{
//        val caret = Caret(this)
//        caret.isVisible = true
//        return caret
//    }
//    private fun getS() : Any{
//        val caret = TextSelection(this)
//        caret.isVisible = true
//        return caret
//    }

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
        if (sourceText.text[sourceText.activeRow].isNotEmpty()) {
            if (sourceText.positionInRow in 1..sourceText.text[sourceText.activeRow].lastIndex) {
                coloringService.paintBrackets(sourceText.activeRow, sourceText.positionInRow + 1, workspaceText[sourceText.activeRow][sourceText.positionInRow], g)
                coloringService.paintBrackets(sourceText.activeRow, sourceText.positionInRow, workspaceText[sourceText.activeRow][sourceText.positionInRow - 1], g)
            }
            else if (sourceText.positionInRow == 0)
                coloringService.paintBrackets(sourceText.activeRow, sourceText.positionInRow + 1, workspaceText[sourceText.activeRow][sourceText.positionInRow], g)
            else if (sourceText.positionInRow == sourceText.text[sourceText.activeRow].length)
                coloringService.paintBrackets(sourceText.activeRow, sourceText.positionInRow, workspaceText[sourceText.activeRow][sourceText.positionInRow - 1], g)
        }
        //(g as Graphics).TextRenderingHint = System.Drawing.Text.TextRenderingHint.AntiAlias;
        //(g as Graphics2D).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT)

        // вывод строки g.drawString(textRow,20,rowY)
        var coordY = 35

        for (string in workspaceText){
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON)
            g.drawString(string,20,coordY)
            coloringService.paintKeyWords(string,g,20,coordY)
            //service.paintKeyWords(string,this,20,coordY)
            coordY+=lineSpacing
        }

        //coordY+=lineSpacing
        caret.paint(g)
    }

    companion object{ // TODO Баг с выделением , надо поиграться и в обратку через строку не выделяет

    }
}