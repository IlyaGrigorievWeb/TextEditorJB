package TextEditorJB.Services

import TextEditorJB.Components.TextPanel

class MouseService (textSelectionService: TextSelectionService, workspaceService : WorkspaceService,textPanel: TextPanel, fileService: FileService) {

    val workspaceService = workspaceService
    val textSelectionService = textSelectionService
    val fileService = fileService
    val panel = textPanel

    fun selectShiftClick(mouseX : Int, mouseY : Int){
        textSelectionService.shiftClick(mouseX,mouseY)
    }
    fun movedMouse (mouseX : Int, mouseY : Int){
        textSelectionService.shiftClick(mouseX,mouseY)
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