package TextEditorJB.Services

import TextEditorJB.Components.TextPanel
import TextEditorJB.Entities.SourceText
import TextEditorJB.Enums.Vector

//Сервис работы с навигацией по тексту
class NavigationService( private val panel: TextPanel) {

    fun setVector (sourceText: SourceText ,vector : Vector)
    {
        when(vector){
            Vector.Down -> sourceText.activeRow++//down
            Vector.Right -> sourceText.positionInRow++ //right
            Vector.Up -> sourceText.activeRow--//up
            Vector.Left -> sourceText.positionInRow-- //left
        }
        if (sourceText.activeRow  < panel.position +  1) //если строка в воркспейсе предпоследняя - скролить
        {
            panel.workspaceService.scrollUp()
        }
        else if ( sourceText.activeRow  > panel.position + panel.rowsInWorkspace - 1)
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
            else
            {
                sourceText.positionInRow = count
                return
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
            setVector(sourceText,Vector.Up)
    }

    fun pageDown (sourceText : SourceText)
    {
        val height = panel.height
        val lines = height / panel.lineSpacing
        for (i in 1..lines)
            setVector(sourceText,Vector.Down)
    }
}