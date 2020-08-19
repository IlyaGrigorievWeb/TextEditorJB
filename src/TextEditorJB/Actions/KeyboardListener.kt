package TextEditorJB.Actions

import TextEditorJB.Components.TextPanel
import java.awt.Font
import java.awt.event.ActionEvent
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class KeyboardListener(panel : TextPanel) : KeyListener{

    var panel = panel
    var navigationService = NavigationActions(panel)

    override fun keyTyped(e: KeyEvent?) {
        var a = 0
    }

    override fun keyPressed(e: KeyEvent?) {
        if (!e!!.isShiftDown)
            panel.textSelection.resetBuffer()
        if (e!!.keyChar != '\uFFFF' && e!!.keyCode != 127 && e!!.keyCode != 10 && e!!.keyCode != 8) { //TODO: Разделить на два класса работу с навигацией и с текстом

                var sb = StringBuilder(panel.fullText[panel.activeRow]) //TODO: Надо игнорировать enter
            sb.insert(panel.caret.positionInRow, e!!.keyChar) //key code делит - 127,бекспейс - 8
            panel.fullText[panel.activeRow] = sb.toString()

            panel.caret.positionInRow++

            //a.caret.leftWidth = charWidth
            var font = Font("Calibri", 0, 20)
            val metrics = panel.getFontMetrics(font)
            val width = metrics.stringWidth(e!!.keyChar.toString())

            //println(metrics.stringWidth(panel.fullText[panel.activeRow]))
            panel.caret.positionX += width

            panel.repaint()
        }
        else if(e!!.keyCode == 8 && panel.caret.positionInRow > 0)
        {
            panel.caret.moveLeft()
            panel.fullText[panel.activeRow] = panel.fullText[panel.activeRow].removeRange(panel.caret.positionInRow, panel.caret.positionInRow+1)
            panel.repaint()
        }
        else{
            when (e!!.keyCode){
                38 -> navigationService.Up()
                40 -> navigationService.Down()
                35 -> navigationService.End()
                36 -> navigationService.Home()
                33 -> navigationService.PageUp()
                34 -> navigationService.PageDown()
            }
            println(e!!.keyChar)
            println(e!!.keyCode) //делит - ,бекспейс - ,инсерт - 155
            println(e!!.extendedKeyCode)
            println(e!!.keyLocation)
            println("_________")
        }

    }

    override fun keyReleased(e: KeyEvent?) {
        var a = 0
    }

}