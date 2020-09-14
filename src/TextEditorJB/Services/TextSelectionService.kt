package TextEditorJB.Services

import TextEditorJB.Components.TextPanel
import TextEditorJB.Entities.SourceText
import java.awt.geom.Rectangle2D

class TextSelectionService(private val panel: TextPanel, private val navigationService : NavigationService) {

    val textSelection get() =  panel.textSelection

    fun shiftLeft (sourceText : SourceText)
    {
        if(!textSelection.drawingSelection){
            textSelection.drawingSelection = true

            textSelection.setEndState()
            navigationService.left(sourceText)
            textSelection.setBeginState()
        }
        else{
            if (sourceText.activeRow == textSelection.selectingStartRow && sourceText.positionInRow == textSelection.selectingStartChar){
                navigationService.left(sourceText)
                textSelection.setBeginState()
            }
            else{
                navigationService.left(sourceText)
                textSelection.setEndState()
            }
        }
    }
    fun shiftRight (sourceText : SourceText)
    {
        if(!textSelection.drawingSelection){
            textSelection.drawingSelection = true

            textSelection.setBeginState()
            navigationService.right(sourceText)
            textSelection.setEndState()
        }
        else{
            if (sourceText.activeRow == textSelection.selectingEndRow && sourceText.positionInRow == textSelection.selectingEndChar){
                navigationService.right(sourceText)
                textSelection.setEndState()
            }
            else{
                navigationService.right(sourceText)
                textSelection.setBeginState()
            }
        }
    }
    fun shiftUp (sourceText : SourceText)
    {
        if(!textSelection.drawingSelection){
            textSelection.drawingSelection = true

            textSelection.setEndState()
            navigationService.up(sourceText)
            textSelection.setBeginState()
        }
        else{
            if (sourceText.activeRow == textSelection.selectingStartRow && sourceText.positionInRow == textSelection.selectingStartChar){
                navigationService.up(sourceText)
                textSelection.setBeginState()
            }
            else{
                navigationService.up(sourceText)
                textSelection.setEndState()
            }
        }
    }
    fun shiftDown (sourceText : SourceText)
    {
        if(!textSelection.drawingSelection){
            textSelection.drawingSelection = true

            textSelection.setBeginState()
            navigationService.down(sourceText)
            textSelection.setEndState()
        }
        else{
            if (sourceText.activeRow == textSelection.selectingEndRow && sourceText.positionInRow == textSelection.selectingEndChar){
                navigationService.down(sourceText)
                textSelection.setEndState()
            }
            else{
                navigationService.down(sourceText)
                textSelection.setBeginState()
            }
        }
    }

    fun shiftClick (mouseX : Int, mouseY : Int,sourceText : SourceText)
    {

        val previousRow  = sourceText.activeRow
        val previousPosition  = sourceText.positionInRow

        panel.caret.setPositionByMouseCoord(mouseX,mouseY)

        if (textSelection.drawingSelection)
        {
            if (textSelection.selectingStartRow == previousRow && textSelection.selectingStartChar == previousPosition)
            { // 123 // Выделение сзади
                if (sourceText.activeRow > textSelection.selectingEndRow  || (sourceText.activeRow == textSelection.selectingEndRow && sourceText.positionInRow > textSelection.selectingEndChar))
                { // 3 // Выделение становится впереди
                    textSelection.setBeginState(textSelection.selectingEndRow,textSelection.selectingEndChar)
                    textSelection.setEndState(sourceText.activeRow,sourceText.positionInRow)
                }
                else
                { // 1 2 //Выделение остается сзади и выделяется либо назад либо вперед
                    textSelection.setBeginState(sourceText.activeRow,sourceText.positionInRow)
                }

            }
            else
            { //456 // Выделение впереди
                if (sourceText.activeRow < textSelection.selectingStartRow  || (sourceText.activeRow == textSelection.selectingStartRow && sourceText.positionInRow < textSelection.selectingStartChar))
                { // 6 // Выделение становится сзади
                    textSelection.setEndState(textSelection.selectingStartRow,textSelection.selectingStartChar)
                    textSelection.setBeginState(sourceText.activeRow,sourceText.positionInRow)
                }
                else
                { // 4 5 //Выделение остается впереди и выделяется либо назад либо вперед
                    textSelection.setEndState(sourceText.activeRow,sourceText.positionInRow)
                }
            }

        }
        else{
            textSelection.drawingSelection = true

            if (sourceText.activeRow > previousRow  || (sourceText.activeRow == previousRow && sourceText.positionInRow > previousPosition)) // Выделяем вперед
            {
                textSelection.setBeginState(previousRow,previousPosition)
                textSelection.setEndState(sourceText.activeRow,sourceText.positionInRow)
            }
            else  { // Выделяем назад
                textSelection.setBeginState(sourceText.activeRow,sourceText.positionInRow)
                textSelection.setEndState(previousRow,previousPosition)
            }
        }
    }

    fun getSelected (sourceText : SourceText) : String
    {
        var resultString = ""
        val selectedTextArray = sourceText.text.subList(textSelection.selectingStartRow,textSelection.selectingEndRow+1)

        if (textSelection.selectingStartRow == textSelection.selectingEndRow)
        {
            resultString = sourceText.text[sourceText.activeRow].substring(textSelection.selectingStartChar,textSelection.selectingEndChar)
        }
        else{
            for ((index) in selectedTextArray.withIndex()) {
                if (index == 0) {
                    resultString += selectedTextArray[index].substring(textSelection.selectingStartChar, selectedTextArray[index].lastIndex+1)
                    resultString += "\n"
                } else if (index == selectedTextArray.lastIndex) {
                    resultString += selectedTextArray[index].substring(0, textSelection.selectingEndChar)
                } else {
                    resultString += selectedTextArray[index]
                    resultString += "\n"
                }
            }
        }

        return resultString
    }

    fun getStringBox (row : Int, beginPosition : Int, endPosition : Int,sourceText : SourceText) : Rectangle2D {

        val metrics = panel.getFontMetrics(panel.textFont)

        val x = panel.borderX + 4 + metrics.stringWidth(sourceText.text[row].substring(0,beginPosition))
        val y = panel.borderY + panel.lineSpacing * (row - panel.position) - metrics.height + 10
        val height = metrics.height - 4
        val width = metrics.stringWidth(sourceText.text[row].substring(beginPosition,endPosition))

        return  Rectangle2D.Double(x.toDouble(),y.toDouble(),width.toDouble(),height.toDouble())

    }
    fun getLineBox (row : Int, beginPosition : Int,sourceText : SourceText) : Rectangle2D {

        val metrics = panel.getFontMetrics(panel.textFont)

        val x = panel.borderX + 4 + metrics.stringWidth(sourceText.text[row].substring(0,beginPosition))
        val y = panel.borderY + panel.lineSpacing * (row - panel.position) - metrics.height + 10
        val height = metrics.height - 4
        val width = panel.size.width

        return  Rectangle2D.Double(x.toDouble(),y.toDouble(),width.toDouble(),height.toDouble())

    }
}