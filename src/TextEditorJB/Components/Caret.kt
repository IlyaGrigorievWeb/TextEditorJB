package TextEditorJB.Components

import java.awt.Font
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JComponent

class Caret( textPanel: TextPanel) : JComponent() {

    var textPanel = textPanel
    var borderX = 16
    var positionX = borderX
    var positionY = 35
    val charCaret = "|"
    var positionInRow = 0;

    fun moveHome() {
        var whitespaces = ""
        var count = 0
        for (char in textPanel.fullText[textPanel.activeRow]){
            if (char.isWhitespace()) {
                whitespaces += char
                count++
            }
        }
        //Regex(pattern = """[\s]*""")
        val metrics = textPanel.getFontMetrics(textPanel.textFont)
        val width = metrics.stringWidth(whitespaces)

        textPanel.caret.positionInRow = count
        textPanel.caret.positionX = width + borderX
    }

    fun setPositionByMouseCoord(mousePositionX: Int,mousePositionY: Int) { //TODO: Косяк с последней строкой
        val metrics = textPanel.getFontMetrics(textPanel.textFont)

        var row = 0;
//        var endY = -1
//        var position = 35
//        while (endY == -1)
//        {
//            if (mousePositionY in position-metrics.height..position)
//            {
//                positionY = position
//                textPanel.activeRow = row
//                endY = positionY
//            }
//            position += textPanel.lineSpacing
//            row++
//        }
        for (position in 35..textPanel.fullText.size*textPanel.lineSpacing step textPanel.lineSpacing){
            if (mousePositionY in position-metrics.height..position-3)
            {
                positionY = 35+row*textPanel.lineSpacing
                textPanel.activeRow = row
            }
            row++
        }
//        for (position in 35..textPanel.fullText.size*textPanel.lineSpacing step textPanel.lineSpacing){
//            if (mousePositionY in position..position+(textPanel.lineSpacing/2))
//            {
//                positionY = 35+row*textPanel.lineSpacing
//                textPanel.activeRow = row
//            }
//            else if(mousePositionY in position..position+textPanel.lineSpacing)
//            {
//                row++
//                positionY = 35+row*textPanel.lineSpacing
//                textPanel.activeRow = row
//
//            }
//            row++
//        }
        if (mousePositionY < 35 - textPanel.lineSpacing)
        {

        }
        //else if(mousePositionY > endY)
        {

        }

        var str = ""
        var posX = borderX;
        var pos = 0;
        var width = 0
        var charWidth = 0
        for (char in textPanel.fullText[textPanel.activeRow]) {
            str += char
            width = metrics.stringWidth(str)
            charWidth = metrics.stringWidth(char.toString())
            if (mousePositionX in posX..posX + charWidth / 2 + 2) {
                positionX = posX
                positionInRow = pos
            } else if (mousePositionX in posX + charWidth / 2 + 2..posX + charWidth) {
                positionX = width + borderX
                positionInRow = pos + 1
            }
            posX = width + borderX
            pos++
        }
        if (mousePositionX < borderX)
        {
            positionX = borderX
            positionInRow = 0
        }
        else if(mousePositionX > width + borderX)
        {
            positionX = width + borderX
            positionInRow = pos
        }
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

            val char = textPanel.fullText[textPanel.activeRow][textPanel.caret.positionInRow].toString()
            val metrics = textPanel.getFontMetrics(textPanel.textFont)
            val width = metrics.stringWidth(char)

            textPanel.caret.positionX += width
            textPanel.caret.positionInRow++
        }
    }
    fun moveUp() {
       // if (textPanel.activeRow > 0) {
            if (textPanel.caret.positionInRow > textPanel.fullText[textPanel.activeRow].lastIndex + 1)
                textPanel.caret.positionInRow = textPanel.fullText[textPanel.activeRow].lastIndex + 1

            val metrics = textPanel.getFontMetrics(textPanel.textFont)
            val width = metrics.stringWidth( textPanel.fullText[textPanel.activeRow].substring(0,textPanel.caret.positionInRow ))

            textPanel.caret.positionX = width + borderX
            textPanel.caret.positionY -= textPanel.lineSpacing
        //}
    }
    fun moveDown() {
        //if (textPanel.fullText.lastIndex > textPanel.activeRow) {
            if (textPanel.caret.positionInRow > textPanel.fullText[textPanel.activeRow].lastIndex + 1)
                textPanel.caret.positionInRow = textPanel.fullText[textPanel.activeRow].lastIndex + 1

            val metrics = textPanel.getFontMetrics(textPanel.textFont)
            val width = metrics.stringWidth( textPanel.fullText[textPanel.activeRow].substring(0,textPanel.caret.positionInRow ))

            textPanel.caret.positionX = width + borderX
            textPanel.caret.positionY += textPanel.lineSpacing
        //}
    }
    fun moveEnd() {
        //if (textPanel.fullText.lastIndex > textPanel.activeRow) {
        textPanel.caret.positionInRow = textPanel.fullText[textPanel.activeRow].lastIndex + 1

        val metrics = textPanel.getFontMetrics(textPanel.textFont)
        val width = metrics.stringWidth(textPanel.fullText[textPanel.activeRow])

        textPanel.caret.positionX = width + borderX
        //}
    }
    fun newLine() //TODO: Переносить строку справа от каретки
    {
        positionY = textPanel.rowY
        positionInRow = 0;
        positionX = 16
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