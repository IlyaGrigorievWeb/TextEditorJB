package TextEditorJB.Actions

import TextEditorJB.Components.TextPanel
import java.awt.event.ActionEvent
import javax.swing.AbstractAction

class ShiftLeft : AbstractAction() {
    override fun actionPerformed(e: ActionEvent?) {
        var actEvent = e as ActionEvent
        var panel = actEvent.source as TextPanel

        var caretIndex = panel.caret.positionInRow

        if (panel.drawingSelection){
            panel.selectingStart--
        }
        else{
            panel.drawingSelection = true;
            panel.selectingStart  = caretIndex - 1
            panel.selectingEnd = caretIndex
        }




        TextPanel.buffer = StringBuilder(TextPanel.buffer).insert(0,TextPanel.textRow[caretIndex-1]).toString();
        LeftAction().actionPerformed(e)

        println(TextPanel.buffer)
    }
}