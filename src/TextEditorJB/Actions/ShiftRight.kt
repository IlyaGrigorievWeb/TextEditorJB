package TextEditorJB.Actions

import TextEditorJB.Components.TextPanel
import java.awt.event.ActionEvent
import javax.swing.AbstractAction

class ShiftRight : AbstractAction() {
    override fun actionPerformed(e: ActionEvent?) {
        var actEvent = e as ActionEvent
        var panel = actEvent.source as TextPanel

        panel.textSelection.selectRight()

        panel.repaint()
    }
}