package TextEditorJB.Listeners

import TextEditorJB.Components.TextPanel
import TextEditorJB.Services.MouseService
import java.awt.event.*

class MouseListener(private val mouseService : MouseService, private val panel : TextPanel) : MouseAdapter() { //сделать 2 метода, получение строки в который пршиел клик и получение позиции символа в который пригел клик //сначала падение, потом выделение //TODO Разделить маус листенеры что бы они не перемешивались

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
    override fun mouseClicked(e: MouseEvent) { //клик
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