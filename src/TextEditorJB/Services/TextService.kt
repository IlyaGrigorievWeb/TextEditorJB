package TextEditorJB.Services

import TextEditorJB.Components.TextPanel
import java.awt.Font

//Сервис работы с текстом
class TextService (textPanel: TextPanel){

    val panel = textPanel

    fun char(char : String)
    {
        var sb = StringBuilder(panel.fullText[panel.activeRow])
        sb.insert(panel.caret.positionInRow, char) //key code делит - 127,бекспейс - 8
        panel.fullText[panel.activeRow] = sb.toString()

        panel.caret.positionInRow++

        //a.caret.leftWidth = charWidth
        var font = Font("Calibri", 0, 20)
        val metrics = panel.getFontMetrics(font)
        val width = metrics.stringWidth(char)


        //println(metrics.stringWidth(panel.fullText[panel.activeRow]))
        panel.caret.positionX += width
    }

    fun enter()
    {
        if (panel.activeRow < panel.fullText.lastIndex){

            var firstPart = panel.fullText.copyOfRange(0,panel.activeRow+1)
            var secondPart = panel.fullText.copyOfRange(panel.activeRow+1,panel.fullText.lastIndex+1)

            var remains = firstPart[firstPart.lastIndex].substring(panel.caret.positionInRow,firstPart[firstPart.lastIndex].lastIndex+1)

            firstPart[firstPart.lastIndex] = firstPart[firstPart.lastIndex].substring(0,panel.caret.positionInRow)

            panel.fullText = firstPart + remains + secondPart
        }
        else{
            panel.activeRow++

            panel.fullText = panel.fullText.copyOf(panel.fullText.size+1) as Array<String>
            panel.fullText[panel.activeRow] = ""
            panel.rowY += panel.lineSpacing
            panel.caret.newLine()
        }
    }
    fun backspace()
    {
        panel.caret.moveLeft()
        panel.fullText[panel.activeRow] = panel.fullText[panel.activeRow].removeRange(panel.caret.positionInRow, panel.caret.positionInRow+1)
    }
    fun delete ()
    {
        if (panel.caret.positionInRow <= panel.fullText[panel.activeRow].length-1)
            panel.fullText[panel.activeRow] = panel.fullText[panel.activeRow].removeRange(panel.caret.positionInRow,panel.caret.positionInRow+1)
    }
    fun insert()
    {
        panel.caret.isInsert = !panel.caret.isInsert
    }

}