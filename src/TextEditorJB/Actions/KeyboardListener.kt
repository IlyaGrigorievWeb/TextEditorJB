package TextEditorJB.Actions

import TextEditorJB.Components.TextPanel
import TextEditorJB.Services.NavigationService
import TextEditorJB.Services.TextSelectionService
import TextEditorJB.Services.TextService
import java.awt.Font
import java.awt.event.ActionEvent
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class KeyboardListener(panel : TextPanel) : KeyListener{

    var panel = panel
    val navigationService = NavigationService(panel)
    val textService = TextService(panel)
    val textSelectionService = TextSelectionService(panel)

    override fun keyTyped(e: KeyEvent?) {
        var a = 0
    }

    override fun keyPressed(e: KeyEvent?) {
        if (e!!.isShiftDown){
            when (e!!.keyCode)
            {
                37 -> textSelectionService.shiftLeft()
                39 -> textSelectionService.shiftRight()
            }
        }
        else {
            when (e!!.keyCode) {
                38 -> navigationService.Up()
                40 -> navigationService.Down()
                37 -> navigationService.Left()
                39 -> navigationService.Right()
                35 -> navigationService.End()
                36 -> navigationService.Home()
                33 -> navigationService.PageUp()
                34 -> navigationService.PageDown()


                155 -> textService.insert()
                8 -> textService.backspace()
                127 -> textService.delete()
                10 -> textService.enter()

                else -> textService.char(e!!.keyChar.toString())
            }
        }
        panel.repaint()
    }

    override fun keyReleased(e: KeyEvent?) {
        var a = 0
    }

}