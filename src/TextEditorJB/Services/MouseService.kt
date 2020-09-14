package TextEditorJB.Services

import TextEditorJB.Components.TextPanel
import TextEditorJB.Entities.SourceText

class MouseService (private val textSelectionService: TextSelectionService, private val workspaceService : WorkspaceService,private  val panel : TextPanel,private val sourceText: SourceText) {

    fun selectShiftClick(mouseX : Int, mouseY : Int){
        textSelectionService.shiftClick(mouseX,mouseY,sourceText)
    }

    fun mouseScroll(rotation : Int){
        if (rotation == 1) //вниз
        {
            workspaceService.scrollDown()
        }
        else if(rotation == -1) //вверх
        {
            workspaceService.scrollUp()
        }
        panel.repaint()
    }
}