package TextEditorJB.Actions

import TextEditorJB.Components.TextPanel
import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection
import java.awt.event.ActionEvent
import javax.swing.AbstractAction

class EnterAction: AbstractAction() {

    override fun actionPerformed(e: ActionEvent?) {
        var actEvent = e as ActionEvent
        var panel = actEvent.source as TextPanel

        if (panel.activeRow < panel.fullText.lastIndex){

        }
        else{
            panel.activeRow++

            panel.fullText = panel.fullText.copyOf(panel.fullText.size+1) as Array<String>
            panel.fullText[panel.activeRow] = ""
            panel.rowY += panel.lineSpacing
            panel.caret.newLine()

            panel.repaint()

        }
    }
}