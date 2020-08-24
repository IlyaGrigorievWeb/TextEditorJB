package TextEditorJB.Services

import TextEditorJB.Components.TextPanel
import java.awt.event.ActionEvent

class TextSelectionService(textPanel: TextPanel) {
    var panel = textPanel

    fun shiftLeft ()
    {
        panel.textSelection.selectLeft()
    }
    fun shiftRight ()
    {
        panel.textSelection.selectRight()
    }
    fun shiftUp ()
    {
        panel.textSelection.selectUp()
    }
    fun shiftDown ()
    {
        panel.textSelection.selectDown()
    }
}