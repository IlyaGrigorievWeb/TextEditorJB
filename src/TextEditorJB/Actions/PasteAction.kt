package TextEditorJB.Actions

import TextEditorJB.Components.TextPanel
import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.StringSelection
import java.awt.event.ActionEvent
import java.lang.StringBuilder
import javax.swing.AbstractAction

class PasteAction : AbstractAction() {

    override fun actionPerformed(e: ActionEvent?) {
        var actEvent = e as ActionEvent
        var panel = actEvent.source as TextPanel

        var clipboard : Clipboard = Toolkit.getDefaultToolkit().systemClipboard
        var dataFlavor : DataFlavor = DataFlavor.stringFlavor
        if (clipboard.isDataFlavorAvailable(dataFlavor)){
            var caretIndex = panel.caret.positionInRow
            var stringBuilder = StringBuilder(panel.fullText[panel.activeRow])
            var pastedText : String = clipboard.getData(dataFlavor).toString()

            println(caretIndex)

            panel.fullText[panel.activeRow] = stringBuilder.insert(caretIndex,pastedText).toString()
            panel.caret.positionInRow = caretIndex + pastedText.length

            println(pastedText.length)
            println(pastedText)
            println(panel.caret.positionInRow)
            println(panel.fullText[panel.activeRow])

            panel.paint(panel.graphics)
        }
    }
}