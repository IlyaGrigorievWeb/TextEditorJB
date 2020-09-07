package TextEditorJB.Components

import TextEditorJB.Entities.SourceText
import java.awt.Color
import java.awt.Font
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.geom.Rectangle2D
import javax.swing.JComponent

class Caret( textPanel: TextPanel,sourceText: SourceText) : JComponent() { //TODO Отрисовка должна проимходить от workspace  а не от всего текста

    val sourceText = sourceText
    var panel = textPanel
    val charCaret = "|"
    //var positionInRow = 0;
    var isInsert = false

    fun setPositionByMouseCoord(mousePositionX: Int,mousePositionY: Int) { //TODO: Долго работает, скорее всего из за того что при клике перерисовывается всё
        val metrics = panel.getFontMetrics(panel.textFont)
        var row = 0;
        if (mousePositionY < panel.borderY - panel.lineSpacing)
        {
            sourceText.activeRow  = 0 + panel.workspaceService.position;
        }
        else {
            for (position in panel.borderY..(panel.borderY - panel.lineSpacing) + sourceText.text.size * panel.lineSpacing step panel.lineSpacing) {
                if (mousePositionY in position - metrics.height + 3..position) {
                    //positionY = panel.borderY + row * panel.lineSpacing
                    sourceText.activeRow = row  + panel.workspaceService.position
                }
                row++
            }
        }
        if (mousePositionX < panel.borderX)
        {
            //positionX = panel.borderX
            sourceText.positionInRow = 0
        }
        else if(mousePositionX > metrics.stringWidth(sourceText.text[sourceText.activeRow]) + panel.borderX)
        {
            sourceText.positionInRow = sourceText.text[sourceText.activeRow].lastIndex + 1
        }
        else{
            var str = ""
            var posX = panel.borderX;
            var pos = 0;
            var width = 0
            var charWidth = 0
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

    override fun paintComponent(g: Graphics?) { //:TODO будет два варианта отрисовки, либо чар , либо прямоугольник размером с символ и перекрашенным сиволом внутри
        var g2 = g as Graphics2D;

        if (sourceText.activeRow in panel.workspaceService.position .. (panel.workspaceService.position + panel.rowsInWorkspace))
        {
            var myFont = Font("Calibri", 0, 20)
            (g as Graphics).font = myFont
            if (isInsert){
                var symbol = ""
                if (sourceText.positionInRow < sourceText.text[sourceText.activeRow].length)
                    symbol = sourceText.text[sourceText.activeRow][sourceText.positionInRow].toString()
                else
                    symbol = " "

                val metrics = panel.getFontMetrics(panel.textFont)

                val row = sourceText.activeRow - panel.workspaceService.position

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
                //g2.drawString(charCaret, positionX, positionY)
                val row = sourceText.activeRow - panel.workspaceService.position
                val charPosition = sourceText.positionInRow

                val metrics = panel.getFontMetrics(panel.textFont)
                val y = panel.borderY  + row * panel.lineSpacing
                val x = metrics.stringWidth(sourceText.text[sourceText.activeRow].substring(0,sourceText.positionInRow)) + panel.borderX

                g2.color = Color.decode("#9400d3")
                g2.drawString(charCaret,x,y)
                g2.color = Color.BLACK
            }
        }

        //TODO Когда пишет заносить скобку когда рисует каретку проверять нет ли рядом скобки и если есть поентиить обе

    }
}