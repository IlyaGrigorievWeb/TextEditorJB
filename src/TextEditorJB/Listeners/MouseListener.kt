package TextEditorJB.Listeners

import TextEditorJB.Components.TextPanel
import TextEditorJB.Services.FileService
import TextEditorJB.Services.MouseService
import java.awt.event.*
import java.awt.event.MouseListener

class MouseListener(mouseService : MouseService) : MouseAdapter() { //сделать 2 метода, получение строки в который пршиел клик и получение позиции символа в который пригел клик //сначала падение, потом выделение //TODO Разделить маус листенеры что бы они не перемешивались

    var pressed = false // TODO Оставить фул текст как есть а уже отрисовку фул текста делать по размеру скролла
    val panel = MyForm.panel as TextPanel
    val mouseService = mouseService

    override fun mouseMoved(e: MouseEvent?) {
        super.mouseMoved(e)
//        var panel = MyForm.panel as TextPanel
        //panel.textSelection.resetBuffer()
        //panel.caret.positionX = panel.caret.setXpositionByMouse(panel.fullText[panel.activeRow],e!!.x)
        //panel.repaint()
    }

    override fun mouseDragged(e: MouseEvent?) {
        if (!panel.textSelection.drawingSelection && !pressed)
        {
            pressed = true
            //panel.textSelection.drawingSelection = true
            panel.caret.setPositionByMouseCoord(e!!.x, e!!.y)
            mouseService.selectShiftClick(e!!.x,e!!.y)
        }
        else if (pressed){
            //panel.caret.setPositionByMouseCoord(e!!.x, e!!.y)
            mouseService.selectShiftClick(e!!.x,e!!.y)
        }
//        super.mouseDragged(e)
//        if (!panel.textSelection.drawingSelection)
//        {
//            panel.textSelection.drawingSelection = true
//            panel.textSelection.setBeginState()
//
//        }
//        else{
//
//        }
//
//
//        if(pressed)
//        {
//            panel.textSelection.selectMouseMotion(e!!.x,e!!.y,pressed)
//        }
        panel.repaint()

    }

     override fun mousePressed(e: MouseEvent?) {
        super.mouseClicked(e)
//        var panel = MyForm.panel as TextPanel
//        panel.textSelection.resetBuffer()
//        //panel.caret.positionX = panel.caret.setXpositionByMouse(panel.fullText[panel.activeRow],e!!.x)
//        panel.repaint()
//         pressed = true
//         panel.textSelection.selectMouseMotion(e!!.x,e!!.y,pressed)
//         if (e!!.clickCount == 0) //делать флаг т.к. это раньше второй не будет реагировать
//         {
//             print(5)
//         }
//         else
//         {
//             print(10)
//         }
    }

    override fun mouseReleased(e: MouseEvent?) {
        super.mouseReleased(e)
        pressed = false
        //panel.textSelection.selectMouseMotion(e!!.x,e!!.y,pressed)
        //println("Отпустил ")
    }
    override fun mouseClicked(e: MouseEvent?) { //клик

            var panel = MyForm.panel as TextPanel
            if (e!!.isShiftDown){
                mouseService.selectShiftClick(e!!.x,e!!.y)
            }
            else {
                panel.textSelection.drawingSelection = false
                panel.caret.setPositionByMouseCoord(e!!.x, e!!.y)
            }
            panel.repaint()

    }

    override fun mouseWheelMoved(e: MouseWheelEvent?) {
        super.mouseWheelMoved(e)
        mouseService.mouseScroll(e!!.wheelRotation)
    }
}
//class MouseListenerWheel : MouseWheelListener {
//    override fun mouseWheelMoved(e: MouseWheelEvent?) {
//
//    }
//
//}
//class MouseListenerDragged : MouseMotionListener{
//    override fun mouseMoved(e: MouseEvent?) {
//        //print("-")
//    }
//
//    override fun mouseDragged(e: MouseEvent?) {
//        print("+")
//        print(e!!.clickCount)
//    }
//
//}