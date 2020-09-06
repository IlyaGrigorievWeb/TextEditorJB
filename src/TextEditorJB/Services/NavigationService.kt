package TextEditorJB.Services

import TextEditorJB.Components.TextPanel
import TextEditorJB.Entities.SourceText

//Сервис работы с навигацией по тексту
class NavigationService(textPanel: TextPanel, sourceText: SourceText,fileService: FileService) {

    val sourceText = sourceText
    val panel = textPanel
    val fileService = fileService
    val linesToScroll = 2

    fun Up ()
    {
        if (sourceText.activeRow > 0){
            sourceText.activeRow--
            //panel.caret.moveUp()
        }
        if (sourceText.text[sourceText.activeRow].lastIndex < sourceText.positionInRow)
            sourceText.positionInRow = sourceText.text[sourceText.activeRow].lastIndex + 1

        if (sourceText.activeRow < panel.workspaceService.position + linesToScroll )
            scrollUp()
    }
    fun Down ()
    {
        if (sourceText.activeRow < sourceText.text.lastIndex){
            sourceText.activeRow++
            //panel.caret.moveDown()
        }
        if (sourceText.text[sourceText.activeRow].lastIndex < sourceText.positionInRow)
            sourceText.positionInRow = sourceText.text[sourceText.activeRow].lastIndex + 1

        if (sourceText.activeRow > panel.workspaceService.position + panel.rowsInWorkspace - linesToScroll)
            scrollDown()
    }
    fun Left ()
    {
        if (sourceText.positionInRow > 0)
            sourceText.positionInRow--
        else
        {
            if (sourceText.activeRow > 0){
                sourceText.activeRow--
                sourceText.positionInRow = panel.workspaceText[sourceText.activeRow].length
            }
        }
        if (sourceText.activeRow < panel.workspaceService.position + linesToScroll)
            scrollUp()
        //panel.caret.moveLeft()
    }
    fun Right ()
    {
        if (sourceText.positionInRow < panel.workspaceText[sourceText.activeRow].length) {
            sourceText.positionInRow++
        }
        else{
            if (sourceText.activeRow < panel.workspaceText.size-1){
                sourceText.activeRow++
                sourceText.positionInRow = 0
            }
        }
        if (sourceText.activeRow > panel.workspaceService.position + panel.rowsInWorkspace - linesToScroll)
            scrollDown()
//        panel.caret.moveRight()
    }
    fun Home ()
    {
        var count = 0
        for (char in sourceText.text[sourceText.activeRow]){
            if (char.isWhitespace()) {
                count++
            }
        }
        sourceText.positionInRow = count
//        panel.caret.moveHome()
    }
    fun End ()
    {
        if (sourceText.text[sourceText.activeRow].lastIndex > sourceText.positionInRow) {
            sourceText.positionInRow = panel.workspaceText[sourceText.activeRow].lastIndex + 1
        }
//        panel.caret.moveEnd()
    }

    fun PageUp ()
    {
        var height = panel.height
        var lines = height / panel.lineSpacing
        for (i in 1..lines)
            Up()
        if (sourceText.activeRow < panel.workspaceService.position + linesToScroll )
            scrollUp()
    }

    fun PageDown ()
    {
        var height = panel.height
        var lines = height / panel.lineSpacing
        for (i in 1..lines)
            Down()
        if (sourceText.activeRow > panel.workspaceService.position + panel.rowsInWorkspace - linesToScroll)
            scrollDown()
    }

    fun scrollUp()
    {
        panel.workspaceService.position--
        panel.workspaceService.setWorkspace()
    }

    fun scrollDown(){
        panel.workspaceService.position++
        fileService.setSourceText(panel.rowsInWorkspace / 2)
        panel.workspaceService.setWorkspace()
    }
    fun NewLine(){
        sourceText.positionInRow = 0;
    }
}