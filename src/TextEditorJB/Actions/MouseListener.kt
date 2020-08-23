package TextEditorJB.Actions

import TextEditorJB.Components.TextPanel
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.event.MouseListener

class MouseListener : MouseAdapter() {
     var pressed = false
     override fun mouseClicked(e: MouseEvent?) {
        super.mouseClicked(e)
        var panel = MyForm.panel as TextPanel
        panel.textSelection.resetBuffer()
        //panel.caret.positionX = panel.caret.setXpositionByMouse(panel.fullText[panel.activeRow],e!!.x)
        panel.repaint()
    }
}
class CustomMouseListener : MouseListener{ //сделать 2 метода, получение строки в который пршиел клик и получение позиции символа в который пригел клик //сначала падение, потом выделение

    var pressed = false

    override fun mouseReleased(e: MouseEvent?) { //отжатие
        var a = 10
    }

    override fun mouseEntered(e: MouseEvent?) { //вход в область
        var a = 10
    }

    override fun mouseClicked(e: MouseEvent?) { //клик
        var panel = MyForm.panel as TextPanel
        if (e!!.isShiftDown){
            panel.textSelection.selectByClick(e!!.x,e!!.y)
        }
        else {
            panel.caret.setPositionByMouseCoord(e!!.x, e!!.y)
        }
        panel.repaint()
    }

    override fun mouseExited(e: MouseEvent?) { //выход из области
        var a = 10
    }

    override fun mousePressed(e: MouseEvent?) { //нажатие
        var a = 10
    }

}