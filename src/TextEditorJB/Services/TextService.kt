package TextEditorJB.Services

import TextEditorJB.Components.TextPanel
import TextEditorJB.Entities.SourceText
import java.awt.Font

//Сервис работы с текстом
class TextService (private val panel: TextPanel,private val sourceText : SourceText,private val navigationService: NavigationService){

//    val panel = textPanel
//    val sourceText = sourceText
//    val navigationService = navigationService

    fun char(char : String)
    {
        if (panel.caret.isInsert)
        {
            if (sourceText.positionInRow <= sourceText.text[sourceText.activeRow].lastIndex) {
                var sb = StringBuilder(sourceText.text[sourceText.activeRow])
                sb.deleteCharAt(sourceText.positionInRow)
                sb.insert(sourceText.positionInRow, char)
                sourceText.text[sourceText.activeRow] = sb.toString()
            }
            else{
                sourceText.text[sourceText.activeRow]+= char
            }

            sourceText.positionInRow++

            //a.caret.leftWidth = charWidth
            var font = Font("Calibri", 0, 20)
            val metrics = panel.getFontMetrics(font)
            val width = metrics.stringWidth(char)


            //println(metrics.stringWidth(panel.fullText[panel.activeRow]))
        }
        else {
            var sb = StringBuilder(sourceText.text[sourceText.activeRow])
            sb.insert(sourceText.positionInRow, char)
            sourceText.text[sourceText.activeRow] = sb.toString()

            sourceText.positionInRow++

            //a.caret.leftWidth = charWidth
            var font = Font("Calibri", 0, 20)
            val metrics = panel.getFontMetrics(font)
            val width = metrics.stringWidth(char)


            //println(metrics.stringWidth(panel.fullText[panel.activeRow]))
        }
        if (char == "{" || char == "}")
            panel.coloringService.bracketsService.addBracket(sourceText.activeRow, sourceText.positionInRow, char[0])

//        workspaceService.setWorkspace()
        //workspaceService.setText(panel.sourceTextService.activeRow,panel.workspaceText[panel.sourceTextService.activeRow])
    }

    fun enter()
    {
        if (sourceText.activeRow <= sourceText.text.lastIndex){

            var firstPart = sourceText.text.copyOfRange(0,sourceText.activeRow+1)
            var secondPart = sourceText.text.copyOfRange(sourceText.activeRow+1,sourceText.text.lastIndex+1)

            var remains = firstPart[firstPart.lastIndex].substring(sourceText.positionInRow,firstPart[firstPart.lastIndex].lastIndex+1)

            firstPart[firstPart.lastIndex] = firstPart[firstPart.lastIndex].substring(0,sourceText.positionInRow)

            sourceText.text = firstPart + remains + secondPart
        }
        else{
            sourceText.text = sourceText.text.copyOf(sourceText.text.size+1) as Array<String>
            sourceText.text[sourceText.activeRow] = ""
            panel.rowY += panel.lineSpacing
        }

        navigationService.Down()
        sourceText.positionInRow = 0
    }
    fun backspace()
    {
        if (!panel.textSelection.drawingSelection) {
            if (!(sourceText.positionInRow <= 0 && sourceText.activeRow <= 0)) {
                navigationService.Left()
                delete()
            }
        }
        else{
            delete()
        }
    }
    fun delete ()
    {
        if (!panel.textSelection.drawingSelection) {
            if(!(sourceText.positionInRow == sourceText.text[sourceText.activeRow].length && sourceText.activeRow == sourceText.text.lastIndex))
            {
                if (sourceText.positionInRow <= sourceText.text[sourceText.activeRow].lastIndex)
                    sourceText.text[sourceText.activeRow] = sourceText.text[sourceText.activeRow].removeRange(sourceText.positionInRow, sourceText.positionInRow + 1)
                else {
                    var firstPart = sourceText.text.copyOfRange(0, sourceText.activeRow + 1)

                    firstPart[sourceText.activeRow] += sourceText.text[sourceText.activeRow + 1]

                    var secondPart = sourceText.text.copyOfRange(sourceText.activeRow + 2, sourceText.text.lastIndex + 1)

                    sourceText.text = firstPart + secondPart
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
                val firstPart = sourceText.text.copyOfRange(0,panel.textSelection.selectingStartRow)
                val thirdPart = sourceText.text.copyOfRange(panel.textSelection.selectingEndRow,sourceText.text.lastIndex)
                val secondPart = sourceText.text[panel.textSelection.selectingStartRow].substring(0,panel.textSelection.selectingStartChar) +
                        sourceText.text[panel.textSelection.selectingEndRow].substring(panel.textSelection.selectingEndChar,sourceText.text[panel.textSelection.selectingEndRow].length)
                sourceText.text = firstPart + secondPart + thirdPart
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
    fun tab()
    {
        sourceText.text[sourceText.activeRow] = sourceText.text[sourceText.activeRow].substring(0,sourceText.positionInRow) +
                "    " + sourceText.text[sourceText.activeRow].substring(sourceText.positionInRow,sourceText.text[sourceText.activeRow].count())
        sourceText.positionInRow += 4
    }

}