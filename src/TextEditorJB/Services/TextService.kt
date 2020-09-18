package TextEditorJB.Services

import TextEditorJB.Components.TextPanel
import TextEditorJB.Entities.SourceText
import TextEditorJB.Enums.Vector

//Сервис работы с текстом
class TextService (private val panel: TextPanel,private val navigationService: NavigationService){

    fun char(char : String,sourceText : SourceText)
    {
        if (panel.caret.isInsert)
        {
            if (sourceText.positionInRow <= sourceText.text[sourceText.activeRow].lastIndex) {
                val sb = StringBuilder(sourceText.text[sourceText.activeRow])
                sb.deleteCharAt(sourceText.positionInRow)
                sb.insert(sourceText.positionInRow, char)
                sourceText.text[sourceText.activeRow] = sb.toString()
            }
            else{
                sourceText.text[sourceText.activeRow]+= char
            }

            sourceText.positionInRow++
        }
        else {
            val sb = StringBuilder(sourceText.text[sourceText.activeRow])
            sb.insert(sourceText.positionInRow, char)
            sourceText.text[sourceText.activeRow] = sb.toString()

            sourceText.positionInRow++
        }
    }

    fun enter(sourceText : SourceText)
    {
        if (sourceText.activeRow <= sourceText.text.lastIndex){

            val remains = sourceText.text[sourceText.activeRow].substring(sourceText.positionInRow, sourceText.text[sourceText.activeRow].lastIndex+1)
            sourceText.text[sourceText.activeRow] = sourceText.text[sourceText.activeRow].substring(0,sourceText.positionInRow)
            sourceText.text.add(sourceText.activeRow+1,remains)
        }
        else{
            sourceText.text.add("")
            sourceText.text[sourceText.activeRow] = ""
            panel.rowY += panel.lineSpacing
        }

        navigationService.setVector(sourceText, Vector.down)
        sourceText.positionInRow = 0
    }
    fun backspace(sourceText : SourceText)
    {
        if (!panel.textSelection.drawingSelection) {
            if (!(sourceText.positionInRow <= 0 && sourceText.activeRow <= 0)) {
                navigationService.setVector(sourceText,Vector.left)
                delete(sourceText)
            }
        }
        else{
            delete(sourceText)
        }
    }
    fun delete (sourceText : SourceText)
    {
        if (!panel.textSelection.drawingSelection) {
            if(!(sourceText.positionInRow == sourceText.text[sourceText.activeRow].length && sourceText.activeRow == sourceText.text.lastIndex))
            {
                if (sourceText.positionInRow <= sourceText.text[sourceText.activeRow].lastIndex)
                    sourceText.text[sourceText.activeRow] = sourceText.text[sourceText.activeRow].removeRange(sourceText.positionInRow, sourceText.positionInRow + 1)
                else {
                    val firstPart = sourceText.text.subList(0, sourceText.activeRow + 1)

                    firstPart[sourceText.activeRow] += sourceText.text[sourceText.activeRow + 1]

                    val secondPart = sourceText.text.subList(sourceText.activeRow + 2, sourceText.text.lastIndex + 1)

                    sourceText.text = firstPart
                    sourceText.text.addAll(secondPart)
                }
            }
        }
        else
        {
            if (panel.textSelection.selectingStartRow == panel.textSelection.selectingEndRow)
            {
                sourceText.text[panel.textSelection.selectingStartRow] = sourceText.text[panel.textSelection.selectingStartRow].removeRange(panel.textSelection.selectingStartChar,panel.textSelection.selectingEndChar)
            }
            else
            {
                val remains = sourceText.text[panel.textSelection.selectingStartRow].substring(0,panel.textSelection.selectingStartChar) +
                        sourceText.text[panel.textSelection.selectingEndRow].substring(panel.textSelection.selectingEndChar,sourceText.text[panel.textSelection.selectingEndRow].length)

                val removedList = sourceText.text.subList(panel.textSelection.selectingStartRow,panel.textSelection.selectingEndRow)

                sourceText.text.removeAll(removedList)
                sourceText.text.add(panel.textSelection.selectingStartRow,remains)

                sourceText.activeRow = panel.textSelection.selectingStartRow
                sourceText.positionInRow = panel.textSelection.selectingStartChar
            }
        }
        panel.textSelection.drawingSelection = false
    }
    fun insert()
    {
        panel.caret.isInsert = !panel.caret.isInsert
    }
    fun tab(sourceText : SourceText)
    {
        sourceText.text[sourceText.activeRow] = sourceText.text[sourceText.activeRow].substring(0,sourceText.positionInRow) +
                "    " + sourceText.text[sourceText.activeRow].substring(sourceText.positionInRow,sourceText.text[sourceText.activeRow].count())
        sourceText.positionInRow += 4
    }

}