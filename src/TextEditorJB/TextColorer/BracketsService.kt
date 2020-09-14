package TextEditorJB.TextColorer

import TextEditorJB.Components.TextPanel
import TextEditorJB.Entities.SourceText
import java.awt.Color
import java.awt.FontMetrics
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.geom.Rectangle2D

class BracketsService (private val panel : TextPanel, private val sourceText: SourceText){

    fun searchBracket (row: Int, position: Int, bracket : Char) : BracketInfo? {
        var searchingRow = row
        var searchingPosition = position

        var counter = 0

        if (bracket == '{') //открывающая
        {
            while(searchingRow != row + panel.rowsInWorkspace * 2 && searchingRow != sourceText.text.lastIndex + 1)
            {
                if (sourceText.text[searchingRow][searchingPosition-1] == '{')
                    counter++
                else if(sourceText.text[searchingRow][searchingPosition-1] == '}')
                    counter--
                if (counter == 0)
                {
                    return BracketInfo(searchingRow,searchingPosition,'}')
                }

                if (searchingPosition - 1 == sourceText.text[searchingRow].lastIndex)
                {
                        searchingRow++
                        searchingPosition = 1
                }
                else
                    searchingPosition++
            }
        }
        else if ( bracket == '}')
        {
            while(searchingRow != row - panel.rowsInWorkspace * 2 && searchingRow != -1)
            {
                if (sourceText.text[searchingRow][searchingPosition-1] == '}')
                    counter++
                else if(sourceText.text[searchingRow][searchingPosition-1] == '{')
                    counter--
                if (counter == 0)
                {
                    return BracketInfo(searchingRow,searchingPosition,'{')
                }

                if (searchingPosition-1 == 0)
                {
                        searchingRow--
                        if(searchingRow!=-1)
                            searchingPosition = sourceText.text[searchingRow].lastIndex -1
                }
                else
                    searchingPosition--
            }
        }
        return null
    }
}