package TextEditorJB.Actions

import TextEditorJB.Components.TextPanel
import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection
import java.awt.event.ActionEvent
import javax.swing.AbstractAction

class CopyAction : AbstractAction() {

    override fun actionPerformed(e: ActionEvent?) {
        var actEvent = e as ActionEvent
        var panel = actEvent.source as TextPanel

        var clipboard : Clipboard = Toolkit.getDefaultToolkit().systemClipboard
        var stringSelection : StringSelection = StringSelection(TextPanel.buffer)
        clipboard.setContents(stringSelection,null)
    }
}