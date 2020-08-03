package TextEditorJB.Actions

import TextEditorJB.Components.TextPanel
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent

class MouseListener : MouseAdapter() {
    override fun mouseClicked(e: MouseEvent?) {
        super.mouseClicked(e)
        var panel = MyForm.panel as TextPanel
        panel.caret.positionX = panel.caret.setXpositionByMouse(TextPanel.strText,e!!.x)
        panel.repaint()
    }
}