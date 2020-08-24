package TextEditorJB.Actions

import TextEditorJB.Components.TextPanel
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.event.MouseListener

class MouseListener : MouseAdapter() { //сделать 2 метода, получение строки в который пршиел клик и получение позиции символа в который пригел клик //сначала падение, потом выделение
    var pressed = true
    val panel = MyForm.panel as TextPanel

    override fun mouseMoved(e: MouseEvent?) {
//        super.mouseMoved(e)
//        var panel = MyForm.panel as TextPanel
//        //panel.textSelection.resetBuffer()
//        //panel.caret.positionX = panel.caret.setXpositionByMouse(panel.fullText[panel.activeRow],e!!.x)
//        panel.repaint()
    }

    override fun mouseDragged(e: MouseEvent?) {
        super.mouseDragged(e)


        if(pressed)
        {
            panel.textSelection.selectMouseMotion(e!!.x,e!!.y,pressed)
        }
        panel.repaint()

    }

     override fun mousePressed(e: MouseEvent?) {
        super.mouseClicked(e)
//        var panel = MyForm.panel as TextPanel
//        panel.textSelection.resetBuffer()
//        //panel.caret.positionX = panel.caret.setXpositionByMouse(panel.fullText[panel.activeRow],e!!.x)
//        panel.repaint()
         //pressed = true
         //panel.textSelection.selectMouseMotion(e!!.x,e!!.y,pressed)
    }

    override fun mouseReleased(e: MouseEvent?) {
        super.mouseReleased(e)

        pressed = false
        //panel.textSelection.selectMouseMotion(e!!.x,e!!.y,pressed)
        //println("Отпустил ")
    }
    override fun mouseClicked(e: MouseEvent?) { //клик
        var panel = MyForm.panel as TextPanel
        panel.textSelection.drawingSelection = false
        if (e!!.isShiftDown){
            panel.textSelection.selectByClick(e!!.x,e!!.y)
        }
        else {
            panel.caret.setPositionByMouseCoord(e!!.x, e!!.y)
        }
        panel.repaint()
    }
}
