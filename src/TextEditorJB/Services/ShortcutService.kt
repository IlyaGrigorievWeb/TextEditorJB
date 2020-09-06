package TextEditorJB.Services

import TextEditorJB.Components.TextPanel
import TextEditorJB.Entities.SourceText
import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.StringSelection

class ShortcutService (textPanel : TextPanel,textSelectionService : TextSelectionService,sourceText : SourceText) {

    val sourceText = sourceText
    val textSelectionService = textSelectionService
    val panel = textPanel

    fun copy(){
        var clipboard : Clipboard = Toolkit.getDefaultToolkit().systemClipboard
        var stringSelection = StringSelection(textSelectionService.getSelected())
        clipboard.setContents(stringSelection,null)
    }

    fun paste(){

        var clipboard : Clipboard = Toolkit.getDefaultToolkit().systemClipboard
        var dataFlavor : DataFlavor = DataFlavor.stringFlavor
        if (clipboard.isDataFlavorAvailable(dataFlavor)) {
            val firstPart = sourceText.text.copyOfRange(0, sourceText.activeRow)
            val thirdPart = sourceText.text.copyOfRange(sourceText.activeRow, sourceText.text.lastIndex)
            val activeRow = sourceText.text[sourceText.activeRow]

            var workString = activeRow.substring(0,sourceText.positionInRow) + clipboard.getData(dataFlavor).toString() + activeRow.substring(sourceText.positionInRow,activeRow.length)

            val secondPart = workString.split("\n")

            sourceText.text = firstPart + secondPart + thirdPart
        }
//        var clipboard : Clipboard = Toolkit.getDefaultToolkit().systemClipboard
//        var dataFlavor : DataFlavor = DataFlavor.stringFlavor
//        if (clipboard.isDataFlavorAvailable(dataFlavor)){
////            var caretIndex = panel.caret.positionInRow
////            var stringBuilder = StringBuilder(panel.fullText[panel.activeRow])
//            //var activeString = panel.fullText[panel.activeRow]
//            var pastedText = clipboard.getData(dataFlavor).toString().split("\n") //TODO: /n переделать на перенос строки И ЧЕРЕЗ РЕГУЛЯРКИ СМОТРЕТЬ /N (перенос строки) и /t (табы)
//
//
//            var firstPart = panel.workspaceText.copyOfRange(0,sourceText.activeRow)
//            var secondPart = panel.workspaceText.copyOfRange(sourceText.activeRow,panel.workspaceText.lastIndex)
//            var firstPartRow = panel.workspaceText[sourceText.activeRow].substring(0,sourceText.positionInRow)
//            var secondPartRow = panel.workspaceText[sourceText.activeRow].substring(sourceText.positionInRow,panel.workspaceText[sourceText.activeRow].lastIndex)
//
//            var resultPart = pastedText.toTypedArray()
//            resultPart[0] = firstPartRow + resultPart[0]
//            val index = resultPart[resultPart.lastIndex].lastIndex
//            resultPart[resultPart.lastIndex] = resultPart[resultPart.lastIndex] + secondPartRow
//
//            panel.workspaceText = firstPart + resultPart + secondPart

//            panel.activeRow += resultPart.lastIndex
//            panel.caret.positionInRow = index + 1
        //}



//            panel.fullText[panel.activeRow] = stringBuilder.insert(caretIndex,pastedText).toString()
//            panel.caret.positionInRow = caretIndex + pastedText.length
    }
}