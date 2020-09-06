package TextEditorJB.Services

import TextEditorJB.Components.TextPanel
import TextEditorJB.Entities.SourceText

//Сервис работы с навигацией по тексту
class NavigationService(textPanel: TextPanel, sourceText: SourceText) {

    val sourceText = sourceText
    val panel = textPanel

    fun Up ()
    {
        if (sourceText.activeRow > 0){
            sourceText.activeRow--
            //panel.caret.moveUp()
        }
        if (sourceText.text[sourceText.activeRow].lastIndex < sourceText.positionInRow)
            sourceText.positionInRow = sourceText.text[sourceText.activeRow].lastIndex + 1
    }
    fun Down ()
    {
        if (sourceText.activeRow < sourceText.text.lastIndex){
            sourceText.activeRow++
            //panel.caret.moveDown()
        }
        if (sourceText.text[sourceText.activeRow].lastIndex < sourceText.positionInRow)
            sourceText.positionInRow = sourceText.text[sourceText.activeRow].lastIndex + 1
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
    }

    fun PageDown ()
    {
        var height = panel.height
        var lines = height / panel.lineSpacing
        for (i in 1..lines)
            Down()
    }

    fun NewLine(){
        sourceText.positionInRow = 0;
    }
}