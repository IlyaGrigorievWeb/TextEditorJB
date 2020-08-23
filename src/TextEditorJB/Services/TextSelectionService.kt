package TextEditorJB.Services

import TextEditorJB.Components.TextPanel
import java.awt.event.ActionEvent

class TextSelectionService(textPanel: TextPanel) {
    var panel = textPanel

    fun shiftLeft ()
    {
        panel.textSelection.selectLeft()
        panel.textSelection.paint(panel.graphics)
    }
    fun shiftRight ()
    {
        panel.textSelection.selectRight()
    }
}