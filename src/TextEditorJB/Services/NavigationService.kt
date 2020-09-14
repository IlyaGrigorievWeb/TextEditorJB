package TextEditorJB.Services

import TextEditorJB.Components.TextPanel
import TextEditorJB.Entities.SourceText

//Сервис работы с навигацией по тексту
class NavigationService( private val panel: TextPanel) {

    fun up (sourceText : SourceText)
    {
        if (sourceText.activeRow > 0){
            sourceText.activeRow--
            if (sourceText.activeRow  < panel.position +  1) //строка в воркспейсе предпоследняя скролить
            {
                panel.workspaceService.scrollUp()
            }

            if (sourceText.text[sourceText.activeRow].lastIndex < sourceText.positionInRow)
                sourceText.positionInRow = sourceText.text[sourceText.activeRow].lastIndex + 1
        }

    }
    fun down (sourceText : SourceText)
    {
        if (sourceText.activeRow < sourceText.text.lastIndex){
            sourceText.activeRow++
            if ( sourceText.activeRow  > panel.position + panel.rowsInWorkspace - 1)
            {
                panel.workspaceService.scrollDown()
            }
            if (sourceText.text[sourceText.activeRow].lastIndex < sourceText.positionInRow)
                sourceText.positionInRow = sourceText.text[sourceText.activeRow].lastIndex + 1
        }

    }
    fun left (sourceText : SourceText)
    {
        if (sourceText.positionInRow > 0)
            sourceText.positionInRow--
        else
        {
            if (sourceText.activeRow > 0){
                sourceText.activeRow--
                sourceText.positionInRow = sourceText.text[sourceText.activeRow].length
            }
        }
        if (sourceText.activeRow  < panel.position +  1) //строка в воркспейсе предпоследняя скролить
        {
            panel.workspaceService.scrollUp()
        }
    }
    fun right (sourceText : SourceText)
    {
        if (sourceText.positionInRow < sourceText.text[sourceText.activeRow].length) {
            sourceText.positionInRow++
        }
        else{
            if (sourceText.activeRow < sourceText.text.lastIndex){
                sourceText.activeRow++
                sourceText.positionInRow = 0
            }
        }
        if ( sourceText.activeRow  > panel.position + panel.rowsInWorkspace - 1)
        {
            panel.workspaceService.scrollDown()
        }
    }
    fun home (sourceText : SourceText)
    {
        var count = 0
        for (char in sourceText.text[sourceText.activeRow]){
            if (char.isWhitespace()) {
                count++
            }
        }
        sourceText.positionInRow = count
    }
    fun end (sourceText : SourceText)
    {
        if (sourceText.text[sourceText.activeRow].lastIndex > sourceText.positionInRow) {
            sourceText.positionInRow = panel.sourceText.text[sourceText.activeRow].lastIndex + 1
        }
    }

    fun pageUp (sourceText : SourceText)
    {
        val height = panel.height
        val lines = height / panel.lineSpacing
        for (i in 1..lines)
            up(sourceText)
    }

    fun pageDown (sourceText : SourceText)
    {
        val height = panel.height
        val lines = height / panel.lineSpacing
        for (i in 1..lines)
            down(sourceText)
    }
}