package TextEditorJB.Components

import TextEditorJB.Entities.SourceText
import java.awt.Color
import java.awt.Font
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.geom.Rectangle2D
import javax.swing.JComponent

class Caret(private val panel: TextPanel,private val sourceText: SourceText) : JComponent() {

    private val charCaret = "|"
    var isInsert = false

    fun setPositionByMouseCoord(mousePositionX: Int,mousePositionY: Int) {
        val metrics = panel.getFontMetrics(panel.textFont)
        var row = 0
        if (mousePositionY < panel.borderY - panel.lineSpacing)
        {
            sourceText.activeRow  = 0 + panel.position
        }
        else {
            for (position in panel.borderY..(panel.borderY - panel.lineSpacing) + sourceText.text.size * panel.lineSpacing step panel.lineSpacing) {
                if (mousePositionY in position - metrics.height + 3..position) {
                    sourceText.activeRow = row  + panel.position
                }
                row++
            }
        }
        if (mousePositionX < panel.borderX)
        {
            sourceText.positionInRow = 0
        }
        else if(mousePositionX > metrics.stringWidth(sourceText.text[sourceText.activeRow]) + panel.borderX)
        {
            sourceText.positionInRow = sourceText.text[sourceText.activeRow].lastIndex + 1
        }
        else{
            var str = ""
            var posX = panel.borderX
            var pos = 0
            var width : Int
            var charWidth :Int
            for (char in sourceText.text[sourceText.activeRow]) {
                str += char
                width = metrics.stringWidth(str)
                charWidth = metrics.stringWidth(char.toString())
                if (mousePositionX in posX..posX + charWidth / 2 + 2) {
                    sourceText.positionInRow = pos
                } else if (mousePositionX in posX + charWidth / 2 + 2..posX + charWidth) {
                    sourceText.positionInRow = pos + 1
                }
                posX = width + panel.borderX
                pos++
            }
        }
    }

    override fun paintComponent(g: Graphics?) {
        val g2 = g as Graphics2D

        if (sourceText.activeRow in panel.position .. (panel.position + panel.rowsInWorkspace))
        {
            (g as Graphics).font = Font("Calibri", 0, 20)
            if (isInsert){
                val symbol: String
                if (sourceText.positionInRow < sourceText.text[sourceText.activeRow].length)
                    symbol = sourceText.text[sourceText.activeRow][sourceText.positionInRow].toString()
                else
                    symbol = " "

                val metrics = panel.getFontMetrics(panel.textFont)

                val row = sourceText.activeRow - panel.position

                val positionX = metrics.stringWidth(sourceText.text[sourceText.activeRow].substring(0,sourceText.positionInRow)) + panel.borderX
                val positionY = panel.borderY  + row * panel.lineSpacing

                val y = positionY - metrics.height + metrics.descent
                val x = positionX + 4
                val h = metrics.height
                val w = metrics.stringWidth(symbol)

                val rectangle =  Rectangle2D.Double(x.toDouble(),y.toDouble(),w.toDouble(),h.toDouble())
                g2.color = Color.MAGENTA
                g2.fill(rectangle)
                g2.color = Color.WHITE
                g2.drawString(symbol, positionX + 4, positionY)
                g2.color = Color.BLACK
            }
            else{
                val row = sourceText.activeRow - panel.position

                val metrics = panel.getFontMetrics(panel.textFont)
                val y = panel.borderY  + row * panel.lineSpacing
                val x = metrics.stringWidth(sourceText.text[sourceText.activeRow].substring(0,sourceText.positionInRow)) + panel.borderX

                g2.color = Color.decode("#9400d3")
                g2.drawString(charCaret,x,y)
                g2.color = Color.BLACK
            }
        }
    }
}