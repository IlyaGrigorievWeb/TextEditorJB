package TextEditorJB.Services

import TextEditorJB.Entities.SourceText
import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.StringSelection

class ShortcutService (private val textSelectionService : TextSelectionService) {

    fun copy(sourceText : SourceText){
        val clipboard : Clipboard = Toolkit.getDefaultToolkit().systemClipboard
        val stringSelection = StringSelection(textSelectionService.getSelected(sourceText))
        clipboard.setContents(stringSelection,null)
    }

    fun paste(sourceText : SourceText){ //к тесту из буфера обмена слева и справа подставляются части активной строки и получившийся текс твставляется в текст

        val clipboard : Clipboard = Toolkit.getDefaultToolkit().systemClipboard
        val dataFlavor : DataFlavor = DataFlavor.stringFlavor
        if (clipboard.isDataFlavorAvailable(dataFlavor)) {

            val activeRow = sourceText.text[sourceText.activeRow]

            val workString = activeRow.substring(0,sourceText.positionInRow) + clipboard.getData(dataFlavor).toString() + activeRow.substring(sourceText.positionInRow,activeRow.length)

            val secondPart = workString.split("\n")

            sourceText.text.removeAt(sourceText.activeRow)
            sourceText.text.addAll(sourceText.activeRow,secondPart)
        }

    }
}