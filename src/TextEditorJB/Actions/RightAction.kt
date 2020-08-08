package TextEditorJB.Actions

import TextEditorJB.Components.Caret
import TextEditorJB.Components.TextPanel
import java.awt.event.ActionEvent
import javax.swing.AbstractAction

class RightAction : AbstractAction() {
    override fun actionPerformed(e: ActionEvent?) {
        var actEvent = e as ActionEvent
        var panel = actEvent.source as TextPanel
        panel.caret.moveRight()
        panel.paint(panel.graphics)
//            var position = 0;
//            var step =0;
//            for (char in TextPanel.textRow)
//            {
//                step = Caret.characterWidthMap[char.toString()]!!
//                position+=step
//
//                if (position >= a.caret.positionX)
//                {a.caret.rightWidth = step}
//            }

    }
}