package TextEditorJB.Components

import TextEditorJB.Entities.SourceText
import TextEditorJB.Services.TextSelectionService
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.geom.Rectangle2D
import javax.swing.JComponent

class TextSelection (private val sourceText: SourceText, private var textSelectionService: TextSelectionService) : JComponent() {

    var selectingStartRow = -1
    var selectingEndRow = -1
    var selectingStartChar = -1
    var selectingEndChar = -1
    var buffer = ""
    var drawingSelection = false

    set(value){
        if (!value) {
            selectingStartRow = -1
            selectingStartChar = -1
            selectingEndRow = -1
            selectingEndChar = -1
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
                    rectangles = mutableListOf(textSelectionService.getStringBox(sourceText,selectingStartRow,selectingStartChar,selectingEndChar))
                }
                1 -> {
                    val firstRectangle =  textSelectionService.getStringBox(sourceText,selectingStartRow,selectingStartChar)
                    val secondRectangle =  textSelectionService.getStringBox(sourceText,selectingEndRow,0,selectingEndChar)
                    rectangles = mutableListOf(firstRectangle,secondRectangle)
                }
                else -> {
                    val firstRectangle =  textSelectionService.getStringBox(sourceText,selectingStartRow,selectingStartChar)
                    rectangles = mutableListOf(firstRectangle)

                    for (row in selectingStartRow+1 until selectingEndRow)
                    {
                        rectangles.add(textSelectionService.getStringBox(sourceText,row,0))
                    }

                    rectangles.add(textSelectionService.getStringBox(sourceText,selectingEndRow,0,selectingEndChar))
                }
            }

            g2.paint = Color.YELLOW
            for (rec in rectangles)
                if (rec.y > 0.toDouble())
                    g2.fill(rec)
            g2.paint = Color.BLACK
        }
    }

    fun setBeginState(row : Int = sourceText.activeRow, position : Int  = sourceText.positionInRow) //суть метода в том что он сохраняет 2 позиции, при этом меньшая всегда стартовая а большая конечная
    {
        selectingStartRow = row
        selectingStartChar = position
        if (selectingEndRow != -1 && (row > selectingEndRow || (row == selectingEndRow && position > selectingEndChar)))
        {
            swapSelectingPositions()
        }
    }
    fun setEndState(row : Int = sourceText.activeRow, position : Int  = sourceText.positionInRow)
    {
        selectingEndRow = row
        selectingEndChar = position
        if (selectingStartRow != -1 && (row < selectingStartRow || (row == selectingStartRow && position < selectingStartChar)))
        {
            swapSelectingPositions()
        }
    }
    private fun swapSelectingPositions()
    {
        val row = selectingStartRow
        val position = selectingStartChar

        selectingStartRow = selectingEndRow
        selectingStartChar =selectingEndChar

        selectingEndRow = row
        selectingEndChar = position
    }
}
