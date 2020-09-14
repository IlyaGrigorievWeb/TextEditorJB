package TextEditorJB.Services

import TextEditorJB.Components.TextPanel
import TextEditorJB.Entities.SourceText

//Сверис работы с областью отображаемой на экране
class WorkspaceService (private val panel : TextPanel,private val sourceText: SourceText) {
    init
    {
        sourceText.register { setWorkspace() }
    }

    fun setWorkspace (){

        if (panel.position + panel.rowsInWorkspace > sourceText.text.lastIndex - 1)
        {
            panel.workspaceText = sourceText.text.subList(panel.position , sourceText.text.count())
        }
        else
        {
            panel.workspaceText = sourceText.text.subList(panel.position,panel.position + panel.rowsInWorkspace + 1)
        }
        panel.repaint()
    }

    fun scrollUp()
    {
        panel.workspaceService.panel.position--
        panel.workspaceService.setWorkspace()
    }

    fun scrollDown(){
        panel.workspaceService.panel.position++
        panel.fileService.readPartial(panel.rowsInWorkspace / 2,sourceText)
        panel.workspaceService.setWorkspace()
    }

}