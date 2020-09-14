package TextEditorJB.Components

import TextEditorJB.Entities.SourceText
import TextEditorJB.Services.TextSelectionService
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.geom.Rectangle2D
import javax.swing.JComponent

class TextSelection (private val sourceText: SourceText, private var textSelectionService: TextSelectionService) : JComponent() {

    var selectingStartRow = 0
    var selectingEndRow = 0
    var selectingStartChar = 0
    var selectingEndChar = 0
    var buffer = ""
    var drawingSelection = false

    set(value){
        if (!value) {
            selectingStartChar = 0
            selectingEndChar = 0
            buffer = ""
        }
        field = value

    }

    override fun paintComponent(g: Graphics?) {

        if (this.drawingSelection)
        {
            val g2 = g as Graphics2D
            val rectangles : MutableList<Rectangle2D>

            when (selectingEndRow - selectingStartRow)
            {
                0 -> {
                    rectangles = mutableListOf(textSelectionService.getStringBox(selectingStartRow,selectingStartChar,selectingEndChar,sourceText))
                }
                1 -> {
                    val firstRectangle =  textSelectionService.getLineBox(selectingStartRow,selectingStartChar,sourceText)
                    val secondRectangle =  textSelectionService.getStringBox(selectingEndRow,0,selectingEndChar,sourceText)
                    rectangles = mutableListOf(firstRectangle,secondRectangle)
                }
                else -> {
                    val firstRectangle =  textSelectionService.getLineBox(selectingStartRow,selectingStartChar,sourceText) //1 с трока ебется
                    rectangles = mutableListOf(firstRectangle)

                    for (row in selectingStartRow+1 until selectingEndRow)
                    {
                        rectangles.add(textSelectionService.getLineBox(row,0,sourceText))
                    }

                    rectangles.add(textSelectionService.getStringBox(selectingEndRow,0,selectingEndChar,sourceText))
                }
            }

            g2.paint = Color.YELLOW
            for (rec in rectangles)
                if (rec.y > 0.toDouble())
                    g2.fill(rec)
            g2.paint = Color.BLACK
        }
    }
    fun setBeginState()
    {
        selectingStartRow = sourceText.activeRow
        selectingStartChar = sourceText.positionInRow
    }
    fun setEndState()
    {
        selectingEndRow = sourceText.activeRow
        selectingEndChar = sourceText.positionInRow
    }
    fun setBeginState(row : Int, position : Int)
    {
        selectingStartRow = row
        selectingStartChar = position
    }
    fun setEndState(row : Int, position : Int)
    {
        selectingEndRow = row
        selectingEndChar = position
    }
}
