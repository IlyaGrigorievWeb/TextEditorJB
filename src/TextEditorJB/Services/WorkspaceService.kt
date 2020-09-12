package TextEditorJB.Services

import TextEditorJB.Components.TextPanel
import TextEditorJB.Entities.SourceText

class WorkspaceService (private val panel : TextPanel,private val sourceText: SourceText) {

//    val sourceText = sourceText
//
//    val panel = textPanel

    var position = 0
        set(value){
//            if (field + panel.rowsInWorkspace <= sourceText.lastIndex && value >= 0) {
            if (value in 0..sourceText.text.lastIndex/*-panel.rowsInWorkspace*/) {
                field = value
            }
        }

    fun setWorkspace (){

        if (position + panel.rowsInWorkspace > sourceText.text.lastIndex - 1)
        {
            panel.workspaceText = sourceText.text.copyOfRange(position , sourceText.text.count())
            //panel.workspaceText += arrayOf(" "," "," ")
            //panel.repaint()
        }
        else
        {
            panel.workspaceText = sourceText.text.copyOfRange(position,position + panel.rowsInWorkspace + 1)
            //panel.repaint()
        }
    }

    fun scrollUp()
    {
        panel.workspaceService.position--
        panel.workspaceService.setWorkspace()
    }

    fun scrollDown(){
        panel.workspaceService.position++
        panel.fileService.setSourceText(panel.rowsInWorkspace / 2)
        panel.workspaceService.setWorkspace()
    }

}