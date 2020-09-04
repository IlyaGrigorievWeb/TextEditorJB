package TextEditorJB.Services

import TextEditorJB.Components.TextPanel
import java.awt.event.ActionEvent

class TextSelectionService(textPanel: TextPanel,navigationService : NavigationService) {

    var panel = textPanel
    val textSelection = panel.textSelection
    var navigation = navigationService

    fun shiftLeft ()
    {
        if(!textSelection.drawingSelection){
            textSelection.drawingSelection = true

            textSelection.setEndState()
            navigation.Left()
            textSelection.setBeginState()
        }
        else{
            if (panel.activeRow == textSelection.selectingStartRow && panel.caret.positionInRow == textSelection.selectingStartChar){
                navigation.Left()
                textSelection.setBeginState()
            }
            else{
                navigation.Left()
                textSelection.setEndState()
            }
        }
    }
    fun shiftRight ()
    {
        if(!textSelection.drawingSelection){
            textSelection.drawingSelection = true

            textSelection.setBeginState()
            navigation.Right()
            textSelection.setEndState()
        }
        else{
            if (panel.activeRow == textSelection.selectingEndRow && panel.caret.positionInRow == textSelection.selectingEndChar){
                navigation.Right()
                textSelection.setEndState()
            }
            else{
                navigation.Right()
                textSelection.setBeginState()
            }
        }
    }
    fun shiftUp ()
    {
        if(!textSelection.drawingSelection){
            textSelection.drawingSelection = true

            textSelection.setEndState()
            navigation.Up()
            textSelection.setBeginState()
        }
        else{
            if (panel.activeRow == textSelection.selectingStartRow && panel.caret.positionInRow == textSelection.selectingStartChar){
                navigation.Up()
                textSelection.setBeginState()
            }
            else{
                navigation.Up()
                textSelection.setEndState()
            }
        }
    }
    fun shiftDown ()
    {
        if(!textSelection.drawingSelection){
            textSelection.drawingSelection = true

            textSelection.setBeginState()
            navigation.Down()
            textSelection.setEndState()
        }
        else{
            if (panel.activeRow == textSelection.selectingEndRow && panel.caret.positionInRow == textSelection.selectingEndChar){
                navigation.Down()
                textSelection.setEndState()
            }
            else{
                navigation.Down()
                textSelection.setBeginState()
            }
        }
    }

    fun shiftClick (mouseX : Int, mouseY : Int)
    {

        val previousRow  = panel.activeRow
        val previousPosition  = panel.caret.positionInRow

        panel.caret.setPositionByMouseCoord(mouseX,mouseY)

        if (textSelection.drawingSelection)
        {
            if (textSelection.selectingStartRow == previousRow && textSelection.selectingStartChar == previousPosition)
            { // 123 // Выделение сзади
                if (panel.activeRow > textSelection.selectingEndRow  || (panel.activeRow == textSelection.selectingEndRow && panel.caret.positionInRow > textSelection.selectingEndChar))
                { // 3 // Выделение становится впереди
                    textSelection.setBeginState(textSelection.selectingEndRow,textSelection.selectingEndChar)
                    textSelection.setEndState(panel.activeRow,panel.caret.positionInRow)
                }
                else
                { // 1 2 //Выделение остается сзади и выделяется либо назад либо вперед
                    textSelection.setBeginState(panel.activeRow,panel.caret.positionInRow)
                }

            }
            else
            { //456 // Выделение впереди
                if (panel.activeRow < textSelection.selectingStartRow  || (panel.activeRow == textSelection.selectingStartRow && panel.caret.positionInRow < textSelection.selectingStartChar))
                { // 6 // Выделение становится сзади
                    textSelection.setEndState(textSelection.selectingStartRow,textSelection.selectingStartChar)
                    textSelection.setBeginState(panel.activeRow,panel.caret.positionInRow)
                }
                else
                { // 4 5 //Выделение остается впереди и выделяется либо назад либо вперед
                    textSelection.setEndState(panel.activeRow,panel.caret.positionInRow)
                }
            }

        }
        else{
            textSelection.drawingSelection = true

            if (panel.activeRow > previousRow  || (panel.activeRow == previousRow && panel.caret.positionInRow > previousPosition)) // Выделяем вперед
            {
                textSelection.setBeginState(previousRow,previousPosition)
                textSelection.setEndState(panel.activeRow,panel.caret.positionInRow)
            }
            else  { // Выделяем назад
                textSelection.setBeginState(panel.activeRow,panel.caret.positionInRow)
                textSelection.setEndState(previousRow,previousPosition)
            }
        }
    }

    fun getSelected () : String
    {
        var resultString = ""
        var selectedTextArray = panel.fullText.copyOfRange(textSelection.selectingStartRow,textSelection.selectingEndRow+1)


        for ((index,string) in selectedTextArray.withIndex()) {
            if (index == 0) {
                resultString += selectedTextArray[index].substring(textSelection.selectingStartChar, selectedTextArray[index].lastIndex)
            } else if (index == selectedTextArray.lastIndex) {
                resultString += selectedTextArray[index].substring(0, textSelection.selectingStartChar)
            } else {
                resultString += selectedTextArray[index]
            }
            resultString += "\n"
        }

        return resultString
    }
}