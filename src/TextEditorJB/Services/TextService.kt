package TextEditorJB.Services

import TextEditorJB.Components.TextPanel
import java.awt.Font

//Сервис работы с текстом
class TextService (textPanel: TextPanel, workspaceService : WorkspaceService){

    val panel = textPanel
    val workspaceService = workspaceService

    fun char(char : String)
    {
        if (panel.caret.isInsert)
        {
            if (panel.caret.positionInRow <= panel.fullText[panel.activeRow].lastIndex) {
                var sb = StringBuilder(panel.fullText[panel.activeRow])
                sb.deleteCharAt(panel.caret.positionInRow)
                sb.insert(panel.caret.positionInRow, char)
                panel.fullText[panel.activeRow] = sb.toString()
            }
            else{
                panel.fullText[panel.activeRow]+= char
            }

            panel.caret.positionInRow++

            //a.caret.leftWidth = charWidth
            var font = Font("Calibri", 0, 20)
            val metrics = panel.getFontMetrics(font)
            val width = metrics.stringWidth(char)


            //println(metrics.stringWidth(panel.fullText[panel.activeRow]))
            panel.caret.positionX += width
        }
        else {
            var sb = StringBuilder(panel.fullText[panel.activeRow])
            sb.insert(panel.caret.positionInRow, char)
            panel.fullText[panel.activeRow] = sb.toString()

            panel.caret.positionInRow++

            //a.caret.leftWidth = charWidth
            var font = Font("Calibri", 0, 20)
            val metrics = panel.getFontMetrics(font)
            val width = metrics.stringWidth(char)


            //println(metrics.stringWidth(panel.fullText[panel.activeRow]))
            panel.caret.positionX += width
        }
        if (char == "{" || char == "}")
            panel.service.stacks.addBracket(panel.activeRow, panel.caret.positionInRow, char[0])


        workspaceService.setText(panel.activeRow,panel.fullText[panel.activeRow])
    }

    fun enter() //TODO пофиксить каретку при переносе
    {
        if (panel.activeRow <= panel.fullText.lastIndex){

            var firstPart = panel.fullText.copyOfRange(0,panel.activeRow+1)
            var secondPart = panel.fullText.copyOfRange(panel.activeRow+1,panel.fullText.lastIndex+1)

            var remains = firstPart[firstPart.lastIndex].substring(panel.caret.positionInRow,firstPart[firstPart.lastIndex].lastIndex+1)

            firstPart[firstPart.lastIndex] = firstPart[firstPart.lastIndex].substring(0,panel.caret.positionInRow)

            panel.fullText = firstPart + remains + secondPart

            panel.activeRow++
            panel.caret.newLine()
        }
        else{
            panel.activeRow++

            panel.fullText = panel.fullText.copyOf(panel.fullText.size+1) as Array<String>
            panel.fullText[panel.activeRow] = ""
            panel.rowY += panel.lineSpacing
            panel.caret.newLine()
        }

        workspaceService.setNewLineText()
    }
    fun backspace()
    {
        panel.caret.moveLeft()
        if (panel.caret.positionInRow < panel.fullText[panel.activeRow].length)
        {
            panel.fullText[panel.activeRow] = panel.fullText[panel.activeRow].removeRange(panel.caret.positionInRow, panel.caret.positionInRow+1)
        }
    }
    fun delete ()
    {
        if (!panel.textSelection.drawingSelection) {
            if (panel.caret.positionInRow <= panel.fullText[panel.activeRow].lastIndex)
                panel.fullText[panel.activeRow] = panel.fullText[panel.activeRow].removeRange(panel.caret.positionInRow, panel.caret.positionInRow + 1)
            else {
                var firstPart = panel.fullText.copyOfRange(0, panel.activeRow + 1)

                firstPart[panel.activeRow] += panel.fullText[panel.activeRow + 1]

                var secondPart = panel.fullText.copyOfRange(panel.activeRow + 2, panel.fullText.lastIndex + 1)

                panel.fullText = firstPart + secondPart
            }
        }
        else
        {
            if (panel.textSelection.selectingStartRow == panel.textSelection.selectingEndRow)
            {
                panel.fullText[panel.textSelection.selectingStartRow]=  panel.fullText[panel.textSelection.selectingStartRow].removeRange(panel.textSelection.selectingStartChar,panel.textSelection.selectingEndChar)
            }
            else
            {
                val firstPart = panel.fullText.copyOfRange(0,panel.textSelection.selectingStartRow)
                val thirdPart = panel.fullText.copyOfRange(panel.textSelection.selectingEndRow,panel.fullText.lastIndex)
                val secondPart = panel.fullText[panel.textSelection.selectingStartRow].substring(0,panel.textSelection.selectingStartChar) +
                        panel.fullText[panel.textSelection.selectingEndRow].substring(panel.textSelection.selectingEndChar,panel.fullText[panel.textSelection.selectingEndRow].lastIndex)
                panel.fullText = firstPart + secondPart + thirdPart
                panel.activeRow = panel.textSelection.selectingStartRow
                panel.caret.positionInRow = panel.textSelection.selectingStartChar
            }
        }
        panel.textSelection.drawingSelection = false
    }
    fun insert()
    {
        panel.caret.isInsert = !panel.caret.isInsert
    }

}