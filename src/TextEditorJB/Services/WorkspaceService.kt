package TextEditorJB.Services

import TextEditorJB.Components.TextPanel
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class WorkspaceService (textPanel: TextPanel) {

    val panel = textPanel
    var position = 0
        set(value){
//            if (field + panel.rowsInWorkspace <= sourceText.lastIndex && value >= 0) {
            if (value in 0..sourceText.lastIndex-panel.rowsInWorkspace) {
                field = value
            }
        }
    var sourceText = arrayOf("")

    fun setWorkspace (){

        if (position + panel.rowsInWorkspace > sourceText.lastIndex)
        {
            panel.fullText = sourceText.copyOfRange(position, sourceText.lastIndex+1)
        }
        else
        {
            panel.fullText = sourceText.copyOfRange(position,position + panel.rowsInWorkspace)
        }
    }
    fun setText (workspaceRow : Int,string : String){
        sourceText[position + workspaceRow] = string
    }
    fun setNewLineText (){
        sourceText = sourceText.plus("")
    }
    fun setWindowInText (workspaceRow : Int,string : String){
        for ((index,workspaceString) in panel.fullText.withIndex())
        {
            if (position+index == sourceText.lastIndex+1)
            {
                sourceText = sourceText.plus(workspaceString)
            }
            else
            {
                sourceText[position+workspaceRow] = workspaceString
            }
        }
    }

}