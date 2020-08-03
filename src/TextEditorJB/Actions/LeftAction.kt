package TextEditorJB.Actions

import TextEditorJB.Components.Caret
import TextEditorJB.Components.TextPanel
import java.awt.event.ActionEvent
import javax.swing.AbstractAction

class LeftAction : AbstractAction() {

    override fun actionPerformed(e: ActionEvent?) {
        var actEvent = e as ActionEvent
        var a = actEvent.source as TextPanel

        if (a.caret.positionInRow >=0){
            a.caret.positionX -= a.caret.leftWidth
            a.caret.positionInRow--

            a.paint(a.graphics)

            var position = 0;
            var step =0;
            for (char in TextPanel.strText)
            {
                step = Caret.characterWidthMap[char.toString()]!!

                if (position >= a.caret.positionX)
                {a.caret.leftWidth = step}

                position+=step
            }
        }
    }
}