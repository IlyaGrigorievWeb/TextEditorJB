package TextEditorJB.Components

import TextEditorJB.Entities.FileInfo
import TextEditorJB.Entities.SourceText
import TextEditorJB.Services.FileService
import TextEditorJB.Services.NavigationService
import TextEditorJB.Services.TextSelectionService
import TextEditorJB.Services.WorkspaceService
import TextEditorJB.TextColorer.TextColorerService
import java.awt.*
import javax.swing.JPanel


class TextPanel (val sourceText: SourceText) : JPanel() {

    //Рабочая область текста отображаемая на экране
    var workspaceText =  listOf<String>()

    var position = 0
        set(value){
            if (value in 0..sourceText.text.lastIndex) {
                field = value
            }
        }

    val borderY = 17 //35
    var rowY = borderY
    var lineSpacing = 20
    val borderX = 16
    var textFont = Font("Calibri",0,20)
    var caret = Caret(this,sourceText)

    val navigationService = NavigationService(this)
    var textSelectionService = TextSelectionService(this,navigationService)
    var coloringService = TextColorerService(this,textSelectionService)
    val workspaceService = WorkspaceService(this, sourceText)
    val fileService = FileService(this,workspaceService, FileInfo())
    var textSelection = TextSelection(sourceText, textSelectionService)


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
                coloringService.paintBrackets(sourceText.activeRow, sourceText.positionInRow + 1, sourceText.text[sourceText.activeRow][sourceText.positionInRow], g,sourceText)
                coloringService.paintBrackets(sourceText.activeRow, sourceText.positionInRow, sourceText.text[sourceText.activeRow][sourceText.positionInRow - 1], g,sourceText)
            }
            else if (sourceText.positionInRow == 0)
                coloringService.paintBrackets(sourceText.activeRow, sourceText.positionInRow + 1, sourceText.text[sourceText.activeRow][sourceText.positionInRow], g,sourceText)
            else if (sourceText.positionInRow == sourceText.text[sourceText.activeRow].length)
                coloringService.paintBrackets(sourceText.activeRow, sourceText.positionInRow, sourceText.text[sourceText.activeRow][sourceText.positionInRow - 1], g,sourceText)
        }

        var coordY = borderY

        for (string in workspaceText.iterator()){
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON)
            g.color = Color.BLACK
            g.drawString(string,20,coordY)
            coloringService.paintKeyWords(string,g,20,coordY)
            coordY+=lineSpacing
        }

        caret.paint(g)
    }
}