package TextEditorJB.Listeners

import TextEditorJB.Components.TextPanel
import TextEditorJB.Services.MouseService
import java.awt.event.*

class MouseListener(private val mouseService : MouseService, private val panel : TextPanel) : MouseAdapter() {

    private var pressed = false

    override fun mouseDragged(e: MouseEvent) {
        if (!panel.textSelection.drawingSelection && !pressed)
        {
            pressed = true
            panel.caret.setPositionByMouseCoord(e.x, e.y)
            mouseService.selectShiftClick(e.x,e.y)
        }
        else if (pressed){
            mouseService.selectShiftClick(e.x,e.y)
        }
        panel.repaint()

    }

    override fun mouseReleased(e: MouseEvent) {
        pressed = false
    }
    override fun mouseClicked(e: MouseEvent) {
        if (e.isShiftDown){
                mouseService.selectShiftClick(e.x,e.y)
        }
        else {
            panel.textSelection.drawingSelection = false
            panel.caret.setPositionByMouseCoord(e.x, e.y)
        }
        panel.repaint()
    }

    override fun mouseWheelMoved(e: MouseWheelEvent?) {
        mouseService.mouseScroll(e!!.wheelRotation)
    }
}