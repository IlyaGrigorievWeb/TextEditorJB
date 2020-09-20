package TextEditorJB.TextColorer

import TextEditorJB.Components.TextPanel
import TextEditorJB.Entities.SourceText

class BracketsService (private val panel : TextPanel, private val sourceText: SourceText){

    val openBrackets = arrayOf('{')
    val closeBrackets = arrayOf('}')

//    fun searchBracket (row: Int, position: Int, bracket : Char) : BracketInfo? {
//        var searchingRow = row
//        var searchingPosition = position
//
//        var counter = 0
//
//        if (bracket == '{') //открывающая
//        {
//            while(searchingRow != row + panel.rowsInWorkspace * 2 && searchingRow != sourceText.text.lastIndex + 1)
//            {
//                if (sourceText.text[searchingRow][searchingPosition-1] == '{')
//                    counter++
//                else if(sourceText.text[searchingRow][searchingPosition-1] == '}')
//                    counter--
//                if (counter == 0)
//                {
//                    return BracketInfo(searchingRow,searchingPosition,'}')
//                }
//
//                if (searchingPosition - 1 == sourceText.text[searchingRow].lastIndex)
//                {
//                        searchingRow++
//                        searchingPosition = 1
//                }
//                else
//                    searchingPosition++
//            }
//        }
//        else if ( bracket == '}')
//        {
//            while(searchingRow != row - panel.rowsInWorkspace * 2 && searchingRow != -1)
//            {
//                if (sourceText.text[searchingRow][searchingPosition-1] == '}')
//                    counter++
//                else if(sourceText.text[searchingRow][searchingPosition-1] == '{')
//                    counter--
//                if (counter == 0)
//                {
//                    return BracketInfo(searchingRow,searchingPosition,'{')
//                }
//
//                if (searchingPosition-1 == 0)
//                {
//                        searchingRow--
//                        if(searchingRow!=-1)
//                            searchingPosition = sourceText.text[searchingRow].lastIndex -1
//                }
//                else
//                    searchingPosition--
//            }
//        }
//        return null
//    }
    fun searchBracket (row: Int, position: Int, bracket : Char) : BracketInfo? {

        var direction = if (openBrackets.contains(bracket)) 1 else if(closeBrackets.contains(bracket)) -1 else 0

        if(direction != 0) {

            var searchingRow = row
            var searchingPosition = position

            var counter = 0

            val pairBracket = if(direction == 1) closeBrackets[openBrackets.indexOf(bracket)] else openBrackets[closeBrackets.indexOf(bracket)]

            while ((searchingRow != row + panel.rowsInWorkspace * 2 && searchingRow != sourceText.text.lastIndex + 1) &&
                    (searchingRow != row - panel.rowsInWorkspace * 2 && searchingRow != -1)) {

                if (sourceText.text[searchingRow][searchingPosition - 1] == bracket)
                    counter++
                else if (sourceText.text[searchingRow][searchingPosition - 1] == pairBracket)
                    counter--
                if (counter == 0) {
                    return BracketInfo(searchingRow, searchingPosition, pairBracket)
                }

                if (searchingPosition - 1 == sourceText.text[searchingRow].lastIndex && direction == 1) {
                    searchingRow += direction
                    searchingPosition = 1
                }
                else if(searchingPosition-1 == 0 && direction == -1) {
                    searchingRow += direction
                    if(searchingRow!=-1)
                        searchingPosition = sourceText.text[searchingRow].lastIndex -1
                }
                else
                    searchingPosition += direction
            }
        }
        return null
    }
}