package TextEditorJB.Services

import TextEditorJB.Components.TextPanel
import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.StringSelection
import java.awt.event.ActionEvent
import java.lang.StringBuilder

class ShortcutService (textSelectionService : TextSelectionService/*,textService: TextService*/) {
    val textSelectionService = textSelectionService
//    val textService = textService

    fun copy(){
        var clipboard : Clipboard = Toolkit.getDefaultToolkit().systemClipboard
        var stringSelection = StringSelection(textSelectionService.getSelected())
        clipboard.setContents(stringSelection,null)
    }

    fun paste(){
        var panel = MyForm.panel as TextPanel

        var clipboard : Clipboard = Toolkit.getDefaultToolkit().systemClipboard
        var dataFlavor : DataFlavor = DataFlavor.stringFlavor
        if (clipboard.isDataFlavorAvailable(dataFlavor)){
//            var caretIndex = panel.caret.positionInRow
//            var stringBuilder = StringBuilder(panel.fullText[panel.activeRow])
            //var activeString = panel.fullText[panel.activeRow]
            var pastedText = clipboard.getData(dataFlavor).toString().split("\n") //TODO: /n переделать на перенос строки И ЧЕРЕЗ РЕГУЛЯРКИ СМОТРЕТЬ /N (перенос строки) и /t (табы)


            var firstPart = panel.fullText.copyOfRange(0,panel.activeRow)
            var secondPart = panel.fullText.copyOfRange(panel.activeRow,panel.fullText.lastIndex)
            var firstPartRow = panel.fullText[panel.activeRow].substring(0,panel.caret.positionInRow)
            var secondPartRow = panel.fullText[panel.activeRow].substring(panel.caret.positionInRow,panel.fullText[panel.activeRow].lastIndex)

            var resultPart = pastedText.toTypedArray()
            resultPart[0] = firstPartRow + resultPart[0]
            val index = resultPart[resultPart.lastIndex].lastIndex
            resultPart[resultPart.lastIndex] = resultPart[resultPart.lastIndex] + secondPartRow

            panel.fullText = firstPart + resultPart + secondPart

//            panel.activeRow += resultPart.lastIndex
//            panel.caret.positionInRow = index + 1
        }



//            panel.fullText[panel.activeRow] = stringBuilder.insert(caretIndex,pastedText).toString()
//            panel.caret.positionInRow = caretIndex + pastedText.length
    }
}