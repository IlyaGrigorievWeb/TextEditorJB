package TextEditorJB.Listeners

import TextEditorJB.Components.TextPanel
import TextEditorJB.Services.*
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent

class KeyboardListener( private val panel : TextPanel,
                        private val navigationService: NavigationService ,
                        private val  textSelectionService: TextSelectionService,
                        private val  textService: TextService,
                        private val  shortcutService: ShortcutService,
                        private val  workspaceService: WorkspaceService) : KeyAdapter(){

    override fun keyPressed(e: KeyEvent) { //TODO При каждом нажатии repaint плохо
        if (e.keyCode != 20 && e.keyCode != 18 && e.keyCode != 524) { //Игнорирование CAPS Lock и Alt т.к. они в данном функционале не нужны
            if (e.isShiftDown) {
                if (e.keyCode != 16) {
                    when (e.keyCode) {
                        38 -> textSelectionService.shiftUp()
                        40 -> textSelectionService.shiftDown()
                        37 -> textSelectionService.shiftLeft()
                        39 -> textSelectionService.shiftRight()
                        8 -> textService.backspace()
                        else -> textService.char(e.keyChar.toString())
                    }
                    workspaceService.setWorkspace()
                    panel.repaint()
                }
            } else if (e.isControlDown) {
                when (e.keyCode) {
                    67 -> shortcutService.copy()
                    86 -> shortcutService.paste()
                    65 -> print('a')
                    88 -> print('x')
                }
            } else {
                //panel.textSelection.drawingSelection = false
                when (e.keyCode) {
                    38 -> navigationService.up()
                    40 -> navigationService.down()
                    37 -> navigationService.left()
                    39 -> navigationService.right()
                    35 -> navigationService.end()
                    36 -> navigationService.home()
                    33 -> navigationService.pageUp()
                    34 -> navigationService.pageDown()


                    155 -> textService.insert()
                    8 -> textService.backspace()
                    127 -> textService.delete()
                    10 -> textService.enter()
                    9 -> textService.tab()

                    else -> textService.char(e.keyChar.toString())
                }
                panel.textSelection.drawingSelection = false
                workspaceService.setWorkspace()
                panel.repaint()
            }
        }
    }

    override fun keyReleased(e: KeyEvent?) {
    }

}