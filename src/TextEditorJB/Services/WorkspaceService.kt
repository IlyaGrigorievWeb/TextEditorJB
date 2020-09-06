package TextEditorJB.Services

import TextEditorJB.Components.TextPanel
import TextEditorJB.Entities.SourceText

class WorkspaceService (textPanel: TextPanel,sourceText: SourceText) {

    val sourceText = sourceText
    val workspaceText : Array<String>
    get() {
        return if (position + panel.rowsInWorkspace > sourceText.text.lastIndex)
        {
            sourceText.text.copyOfRange(position, sourceText.text.lastIndex+1)
        }
        else
        {
            sourceText.text.copyOfRange(position,position + panel.rowsInWorkspace)
        }
    }
    val panel = textPanel
    var position = 0
        set(value){
//            if (field + panel.rowsInWorkspace <= sourceText.lastIndex && value >= 0) {
            if (value in 0..sourceText.text.lastIndex-panel.rowsInWorkspace) {
                field = value
            }
        }

    fun setWorkspace (){

        if (position + panel.rowsInWorkspace > sourceText.text.lastIndex)
        {
            panel.workspaceText = sourceText.text.copyOfRange(position, sourceText.text.lastIndex+1)
            panel.repaint()
        }
        else
        {
            panel.repaint()
            panel.workspaceText = sourceText.text.copyOfRange(position,position + panel.rowsInWorkspace)
        }
    }

}