package TextEditorJB.Services

class MouseService (textSelectionService: TextSelectionService){
    val textSelectionService = textSelectionService

    fun selectShiftClick(mouseX : Int, mouseY : Int){
        textSelectionService.shiftClick(mouseX,mouseY)
    }
}