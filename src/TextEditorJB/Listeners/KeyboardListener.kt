package TextEditorJB.Listeners

import TextEditorJB.Components.TextPanel
import TextEditorJB.Entities.SourceText
import TextEditorJB.Enums.Vector
import TextEditorJB.Services.*
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent

class KeyboardListener( private val panel : TextPanel,
                        private val navigationService: NavigationService ,
                        private val  textSelectionService: TextSelectionService,
                        private val  textService: TextService,
                        private val  shortcutService: ShortcutService,
                        private val  workspaceService: WorkspaceService,
                        private val sourceText: SourceText) : KeyAdapter(){

    override fun keyPressed(e: KeyEvent) { //TODO При каждом нажатии repaint плохо
        if (e.keyCode != 20 && e.keyCode != 18 && e.keyCode != 524) { //Игнорирование CAPS Lock и Alt т.к. они в данном функционале не нужны
            if (e.isShiftDown) {
                if (e.keyCode != 16) {
                    when (e.keyCode) {
//                        38 -> textSelectionService.shiftUp(sourceText)
//                        40 -> textSelectionService.shiftDown(sourceText)
//                        37 -> textSelectionService.shiftLeft(sourceText)
//                        39 -> textSelectionService.shiftRight(sourceText)
                        38 -> textSelectionService.shiftNavigation(sourceText,Vector.up)
                        40 -> textSelectionService.shiftNavigation(sourceText,Vector.down)
                        37 -> textSelectionService.shiftNavigation(sourceText,Vector.left)
                        39 -> textSelectionService.shiftNavigation(sourceText,Vector.right)
                        8 -> textService.backspace(sourceText)
                        else -> textService.char(e.keyChar.toString(),sourceText)
                    }
                    workspaceService.setWorkspace()
                    panel.repaint()
                }
            } else if (e.isControlDown) {
                when (e.keyCode) {
                    67 -> shortcutService.copy(sourceText)
                    86 -> shortcutService.paste(sourceText)
                    65 -> print('a')
                    88 -> print('x')
                }
                panel.textSelection.drawingSelection = false
                workspaceService.setWorkspace()
                panel.repaint()
            } else {
                //panel.textSelection.drawingSelection = false
                when (e.keyCode) {
//                    38 -> navigationService.up(sourceText)
//                    40 -> navigationService.down(sourceText)
//                    37 -> navigationService.left(sourceText)
//                    39 -> navigationService.right(sourceText)
//                    0 -> sourceText.activeRow++//down
//                    1 -> sourceText.positionInRow++ //right
//                    2 -> sourceText.activeRow--//up
//                    3 -> sourceText.positionInRow-- //left
                    38 -> navigationService.setVector(sourceText,Vector.up)
                    40 -> navigationService.setVector(sourceText,Vector.down)
                    37 -> navigationService.setVector(sourceText,Vector.left)
                    39 -> navigationService.setVector(sourceText,Vector.right)
                    35 -> navigationService.end(sourceText)
                    36 -> navigationService.home(sourceText)
                    33 -> navigationService.pageUp(sourceText)
                    34 -> navigationService.pageDown(sourceText)


                    155 -> textService.insert()
                    8 -> textService.backspace(sourceText)
                    127 -> textService.delete(sourceText)
                    10 -> textService.enter(sourceText)
                    9 -> textService.tab(sourceText)

                    else -> textService.char(e.keyChar.toString(),sourceText)
                }
                panel.textSelection.drawingSelection = false
                workspaceService.setWorkspace()
                panel.repaint()
            }
        }
    }
}