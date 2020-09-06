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
            //if (TextPanel.position + 30 <= TextPanel.text.lastIndex) {
            panel.navigationService.scrollDown()
            //}
//            if (TextPanel.position > 0)
//            {
//                var rowsCount = 50
//                TextPanel.position--
//                panel.fullText = TextPanel.text.copyOfRange(TextPanel.position,TextPanel.position + rowsCount)
//                panel.repaint()
//            }
        }
        else if(rotation == -1) //вверх
        {
//            if (TextPanel.position > 0) {
            panel.navigationService.scrollUp()
            //}
//            if (TextPanel.position + 50 <= TextPanel.text.lastIndex) {
//                var rowsCount = 50
//                TextPanel.position++
//                panel.fullText = TextPanel.text.copyOfRange(TextPanel.position, TextPanel.position + rowsCount)
//                panel.repaint()
//            }
        }
        panel.repaint()
    }
}