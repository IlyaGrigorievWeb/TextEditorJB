package TextEditorJB.Components

import TextEditorJB.Entities.SourceText
import TextEditorJB.Services.FileService
import TextEditorJB.Services.NavigationService
import TextEditorJB.Services.WorkspaceService
import TextEditorJB.TextColorer.TextColorerService
import java.awt.*
import javax.swing.JPanel


class TextPanel (private val sourceText: SourceText) : JPanel() {

    var workspaceText =  arrayOf("")

    val borderY = 17 //35
    var rowY = borderY
    var lineSpacing = 20
    val borderX = 16
    var textFont = Font("Calibri",0,20)
    var caret = Caret(this,sourceText)


    var coloringService = TextColorerService(this)
    val workspaceService = WorkspaceService(this, sourceText)
    val fileService = FileService(this,sourceText,workspaceService)
    val navigationService = NavigationService(this,sourceText)
    var textSelection = TextSelection(this,sourceText, navigationService)

    val rowsInWorkspace : Int
        get() {
            return (this.size.height / lineSpacing) - 1
        }

    override fun paintComponent(g: Graphics?)  {
        val g2 = g as Graphics2D
        super.paintComponent(g)

        textSelection.paint(g)

        (g as Graphics).font = textFont
        if (sourceText.text[sourceText.activeRow].isNotEmpty()) {
            if (sourceText.positionInRow in 1..sourceText.text[sourceText.activeRow].lastIndex) {
                coloringService.paintBrackets(sourceText.activeRow, sourceText.positionInRow + 1, sourceText.text[sourceText.activeRow][sourceText.positionInRow], g)
                coloringService.paintBrackets(sourceText.activeRow, sourceText.positionInRow, sourceText.text[sourceText.activeRow][sourceText.positionInRow - 1], g)
            }
            else if (sourceText.positionInRow == 0)
                coloringService.paintBrackets(sourceText.activeRow, sourceText.positionInRow + 1, sourceText.text[sourceText.activeRow][sourceText.positionInRow], g)
            else if (sourceText.positionInRow == sourceText.text[sourceText.activeRow].length)
                coloringService.paintBrackets(sourceText.activeRow, sourceText.positionInRow, sourceText.text[sourceText.activeRow][sourceText.positionInRow - 1], g)
        }

        var coordY = borderY

        for (string in workspaceText){
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON)
            g.color = Color.BLACK
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