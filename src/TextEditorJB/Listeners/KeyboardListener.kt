package TextEditorJB.Listeners

import TextEditorJB.Components.TextPanel
import TextEditorJB.Services.*
import java.awt.Font
import java.awt.event.ActionEvent
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class KeyboardListener( panel : TextPanel,
                        navigationService: NavigationService ,
                        textSelectionService: TextSelectionService,
                        fileService: FileService,
                        textService: TextService) : KeyListener{

    var panel = panel
    val navigationService = navigationService
    val textService = textService
    val textSelectionService = textSelectionService
    val shortcutService = ShortcutService(textSelectionService)
    val fileService = fileService

    override fun keyTyped(e: KeyEvent?) {
    }

    override fun keyPressed(e: KeyEvent?) {
        if (e!!.isShiftDown){
            if (e!!.keyCode != 16){
                when (e!!.keyCode)
                {
                    38 -> textSelectionService.shiftUp()
                    40 -> textSelectionService.shiftDown()
                    37 -> textSelectionService.shiftLeft()
                    39 -> textSelectionService.shiftRight()
                    else -> textService.char(e!!.keyChar.toString())
                }
            }
        }
        else if(e!!.isControlDown)
        {
            when (e!!.keyCode)
            {
                67 -> shortcutService.copy()
                86 -> shortcutService.paste()
                65 -> print('a')
                88 -> print('x')
            }
        }
        else {
            //panel.textSelection.drawingSelection = false
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
    }

}