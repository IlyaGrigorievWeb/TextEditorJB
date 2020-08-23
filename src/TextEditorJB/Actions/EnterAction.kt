package TextEditorJB.Actions

import TextEditorJB.Components.TextPanel
import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection
import java.awt.event.ActionEvent
import javax.swing.AbstractAction

class EnterAction: AbstractAction() {

    override fun actionPerformed(e: ActionEvent?) {
//        var actEvent = e as ActionEvent
//        var panel = actEvent.source as TextPanel
//
//        if (panel.activeRow < panel.fullText.lastIndex){
//
//            var firstPart = panel.fullText.copyOfRange(0,panel.activeRow+1)
//            var secondPart = panel.fullText.copyOfRange(panel.activeRow+1,panel.fullText.lastIndex+1)
//
//            var remains = firstPart[firstPart.lastIndex].substring(panel.caret.positionInRow,firstPart[firstPart.lastIndex].lastIndex+1)
//
//            firstPart[firstPart.lastIndex] = firstPart[firstPart.lastIndex].substring(0,panel.caret.positionInRow)
//
//            panel.fullText = firstPart + remains + secondPart
//
//            panel.repaint()
//
//        }
//        else{
//            panel.activeRow++
//
//            panel.fullText = panel.fullText.copyOf(panel.fullText.size+1) as Array<String>
//            panel.fullText[panel.activeRow] = ""
//            panel.rowY += panel.lineSpacing
//            panel.caret.newLine()
//
//            panel.repaint()
//
//        }
    }
}