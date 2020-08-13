package TextEditorJB.Components

import java.awt.Font
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JComponent

class Caret( textPanel: TextPanel) : JComponent() {

    var textPanel = textPanel
    var positionX = 16
    var positionY = 35
    val charCaret = "|"
    var positionInRow = 0;

    fun reset() {
        positionY = textPanel.rowY
        positionInRow = 0;
        positionX = 16
    }

    fun setXpositionByMouse(string: String, mousePositionX: Int): Int { //TODO: Не точно падает мышка, думаю дело в курсоре
        textPanel.drawingSelection = false;
        var positionX = 16
        val metrics = textPanel.getFontMetrics(textPanel.textFont)
        for (ch in string) {
            if (positionX > mousePositionX) {
                return positionX
            }
            positionX += metrics.stringWidth(ch.toString())
        }
        return positionX
    }

    override fun paintComponent(g: Graphics?) {
        //super.paintComponent(g)


        var g2 = g as Graphics2D;

        var myFont: Font = Font("Calibri", 0, 20)
        (g as Graphics).font = myFont

        g2.drawString(charCaret, positionX, positionY)


//        var q = "qwerhqhwfoqwiefjoqwiefefjqoiwejfowjefoqjweijfqjojewfjqowjerojjqwer"
//        var str = arrayListOf<String>()
//        for(i in 1..99) {
//            val randomValues = List(100) { Random.nextInt(0, q.lastIndex) }
//            var st = ""
//            for(k in 1..99) {
//                st += q[randomValues[k]]
//            }
//            str.add(st)
//        }
//        var a = 30
//        this.graphics.font = Font("Calibri",0,20)
//        for(i in str) {
//            this.graphics.drawString(i,20,a)
//            a+=10
//        }
    }

    fun moveLeft() { //TODO: Люфт влево-право +-1
        if (textPanel.caret.positionInRow > 0) {

            val char = textPanel.fullText[textPanel.activeRow][textPanel.caret.positionInRow - 1].toString()
            val metrics = textPanel.getFontMetrics(textPanel.textFont)
            val width = metrics.stringWidth(char)

            textPanel.caret.positionX -= width
            textPanel.caret.positionInRow--
        }
    }

    fun moveRight() { //TODO: Люфт влево-право +-1
        if (textPanel.caret.positionInRow < textPanel.fullText[textPanel.activeRow].length) {

            val char = textPanel.fullText[textPanel.activeRow][textPanel.caret.positionInRow - 1].toString()
            val metrics = textPanel.getFontMetrics(textPanel.textFont)
            val width = metrics.stringWidth(char)

            textPanel.caret.positionX += width
            textPanel.caret.positionInRow++

            textPanel.paint(textPanel.graphics)
        }
    }

    fun newLine()
    {

    }

    companion object {
//        val lineInterval = 3
//        val characterWidthMap: Map<String, Int> = mapOf(
//            "q" to 4, "w" to 10, "e" to 7 /*хорошо*/, "r" to 6 ,
//            "t" to 4 /*хорошо*/, "y" to 6, "u" to 6, "i" to 2,
//            "o" to 6, "p" to 6, "a" to 5, "s" to 4,
//            "d" to 6, "f" to 4, "g" to 6, "h" to 6,
//            "j" to 2, "k" to 4, "l" to 2, "z" to 4,
//            "x" to 4, "c" to 4, "v" to 6, "b" to 6,
//            "n" to 6, "m" to 12, " " to 3
//        )
    }
}