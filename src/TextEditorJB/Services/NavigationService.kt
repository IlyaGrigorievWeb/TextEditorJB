package TextEditorJB.Services

import TextEditorJB.Components.TextPanel
import TextEditorJB.Entities.SourceText

//Сервис работы с навигацией по тексту
class NavigationService( private val panel: TextPanel,private val sourceText: SourceText) {

    fun up ()
    {
        if (sourceText.activeRow > 0){
            sourceText.activeRow--
            //panel.caret.moveUp()
            if (sourceText.activeRow  < panel.workspaceService.position +  1) //строка в воркспейсе предпоследняя скролить
            {
                panel.workspaceService.scrollUp()
            }

            if (sourceText.text[sourceText.activeRow].lastIndex < sourceText.positionInRow)
                sourceText.positionInRow = sourceText.text[sourceText.activeRow].lastIndex + 1
        }

    }
    fun down ()
    {
        if (sourceText.activeRow < sourceText.text.lastIndex){
            sourceText.activeRow++
            //panel.caret.moveDown()
            if ( sourceText.activeRow  > panel.workspaceService.position + panel.rowsInWorkspace - 1)
            {
                panel.workspaceService.scrollDown()
            }
            if (sourceText.text[sourceText.activeRow].lastIndex < sourceText.positionInRow)
                sourceText.positionInRow = sourceText.text[sourceText.activeRow].lastIndex + 1
        }

    }
    fun left ()
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
        if (sourceText.activeRow  < panel.workspaceService.position +  1) //строка в воркспейсе предпоследняя скролить
        {
            panel.workspaceService.scrollUp()
        }

        //panel.caret.moveLeft()
    }
    fun right ()
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
        if ( sourceText.activeRow  > panel.workspaceService.position + panel.rowsInWorkspace - 1)
        {
            panel.workspaceService.scrollDown()
        }

//        panel.caret.moveRight()
    }
    fun home ()
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
    fun end ()
    {
        if (sourceText.text[sourceText.activeRow].lastIndex > sourceText.positionInRow) {
            sourceText.positionInRow = panel.sourceText.text[sourceText.activeRow].lastIndex + 1
        }
//        panel.caret.moveEnd()
    }

    fun pageUp ()
    {
        val height = panel.height
        val lines = height / panel.lineSpacing
        for (i in 1..lines)
            up()
    }

    fun pageDown ()
    {
        val height = panel.height
        val lines = height / panel.lineSpacing
        for (i in 1..lines)
            down()
    }

    fun newLine(){
        sourceText.positionInRow = 0
    }
}