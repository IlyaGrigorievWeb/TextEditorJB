package TextEditorJB.Actions

import TextEditorJB.Components.Caret
import TextEditorJB.Components.TextPanel
import java.awt.Font
import java.awt.event.ActionEvent
import javax.swing.AbstractAction

class LeftAction : AbstractAction() {

    override fun actionPerformed(e: ActionEvent?) {
        var actEvent = e as ActionEvent
        var panel = actEvent.source as TextPanel
        var a = panel.fullText
        panel.caret.moveLeft()
        panel.paint(panel.graphics)

//            var position = 0;
//            var step =0;
//            for (char in TextPanel.textRow)
//            {
//                step = Caret.characterWidthMap[char.toString()]!!
//
//                if (position >= a.caret.positionX)
//                {a.caret.leftWidth = step}
//
//                position+=step
//            }

    }
}