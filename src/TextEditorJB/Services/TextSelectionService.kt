package TextEditorJB.Services

import TextEditorJB.Components.TextPanel
import TextEditorJB.Entities.SourceText

class TextSelectionService(textPanel: TextPanel,navigationService : NavigationService,sourceText: SourceText) {

    var panel = textPanel
    val sourceText = sourceText
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
            if (sourceText.activeRow == textSelection.selectingStartRow && sourceText.positionInRow == textSelection.selectingStartChar){
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
            if (sourceText.activeRow == textSelection.selectingEndRow && sourceText.positionInRow == textSelection.selectingEndChar){
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
            if (sourceText.activeRow == textSelection.selectingStartRow && sourceText.positionInRow == textSelection.selectingStartChar){
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
            if (sourceText.activeRow == textSelection.selectingEndRow && sourceText.positionInRow == textSelection.selectingEndChar){
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

    fun getSelected () : String
    {
        var resultString = ""
        var selectedTextArray = sourceText.text.copyOfRange(textSelection.selectingStartRow,textSelection.selectingEndRow+1)

        if (textSelection.selectingStartRow == textSelection.selectingEndRow)
        {
            resultString = sourceText.text[sourceText.activeRow].substring(textSelection.selectingStartChar,textSelection.selectingEndChar)
        }
        else{
            for ((index,string) in selectedTextArray.withIndex()) {
                if (index == 0) {
                    resultString += selectedTextArray[index].substring(textSelection.selectingStartChar, selectedTextArray[index].lastIndex+1)
                    resultString += "\n"
                } else if (index == selectedTextArray.lastIndex) {
                    resultString += selectedTextArray[index].substring(0, textSelection.selectingStartChar)
                } else {
                    resultString += selectedTextArray[index]
                    resultString += "\n"
                }
            }
        }

        return resultString
    }
}