package TextEditorJB.Actions

import TextEditorJB.Components.TextPanel
import java.awt.event.ActionEvent
import javax.swing.AbstractAction

class ShiftRight : AbstractAction() {
    override fun actionPerformed(e: ActionEvent?) {
        var actEvent = e as ActionEvent
        var panel = actEvent.source as TextPanel

        var caretIndex = panel.caret.positionInRow

        if (panel.drawingSelection){
            panel.selectingStart++
        }
        else{
            panel.drawingSelection = true;
            panel.selectingStart  = caretIndex
            panel.selectingEnd = caretIndex + 1
        }

        TextPanel.buffer += panel.fullText[panel.activeRow][caretIndex-1]
        RightAction().actionPerformed(e)

        println(TextPanel.buffer)
    }
}