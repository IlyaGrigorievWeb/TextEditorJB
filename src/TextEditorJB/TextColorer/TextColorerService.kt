package TextEditorJB.TextColorer

import TextEditorJB.Components.TextPanel
import java.awt.Color
import java.awt.FontMetrics
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.geom.Rectangle2D

class TextColorerService (textPanel : TextPanel) {
    val panel = textPanel
    var bracketsService = BracketsService(panel, panel.sourceText)
    fun paintKeyWords(string: String, graphics: Graphics, x: Int, y: Int) { //сколько скобок от начала столько скобок от конца
        //var graphics = panel.graphics
        val metrics = graphics.getFontMetrics(graphics.font)
        var str = ""

        var words = string.split(" ")
        for ((index, word) in words.withIndex()) {
            if (types.containsKey(word)) {
                setIdentifier(words[index + 1])
            }
            if (keyWordsColors.containsKey(word)) {
                graphics.color = keyWordsColors[word]
                graphics.drawString("$word ", x + metrics.stringWidth(str), y)
                graphics.color = Color.BLACK
            }
            //else if(word.indexOf("//") != -1)
            else if (word == "//" || word.startsWith("//")) {
                graphics.color = Color.PINK
                graphics.drawString("$word ", x + metrics.stringWidth(str), y)
            } else {
                graphics.drawString("$word ", x + metrics.stringWidth(str), y)
            }
            str += "$word "
        }
        graphics.color = Color.BLACK
    }

    val keyWordsColors: MutableMap<String, Color> = mutableMapOf(
            "byte" to Color.BLUE, "short" to Color.BLUE, "int" to Color.BLUE, "long" to Color.BLUE,
            "char" to Color.BLUE, "float" to Color.BLUE, "double" to Color.BLUE, "boolean" to Color.BLUE,
            "if" to Color.GREEN, "else" to Color.GREEN, "switch" to Color.GREEN, "case" to Color.BLUE,
            "default" to Color.BLUE, "while" to Color.BLUE, "do" to Color.BLUE, "break" to Color.BLUE,
            "continue" to Color.BLUE, "for" to Color.BLUE, "try" to Color.BLUE, "catch" to Color.BLUE,
            "finally" to Color.BLUE, "throw" to Color.BLUE, "throws" to Color.BLUE, "private" to Color.BLUE,
            "protected" to Color.BLUE, "public" to Color.BLUE, "import" to Color.BLUE, "package" to Color.BLUE,
            "class" to Color.BLUE, "interface" to Color.BLUE, "extends" to Color.BLUE, "implements" to Color.BLUE,
            "static" to Color.BLUE, "final" to Color.BLUE, "void" to Color.BLUE, "abstract" to Color.BLUE,
            "new" to Color.BLUE, "return" to Color.BLUE, "this" to Color.BLUE, "super" to Color.BLUE,
            "synchronized" to Color.BLUE, "volatile" to Color.BLUE, "const" to Color.BLUE, "goto" to Color.BLUE,
            "instanceof" to Color.BLUE, "enum" to Color.BLUE, "assert" to Color.BLUE, "transient" to Color.BLUE,
            "strictfp" to Color.BLUE
    )
    val types: MutableMap<String, Color> = mutableMapOf(
            "byte" to Color.BLUE, "short" to Color.BLUE, "int" to Color.BLUE, "long" to Color.BLUE,
            "char" to Color.BLUE, "float" to Color.BLUE, "double" to Color.BLUE, "boolean" to Color.BLUE,
            "var" to Color.GREEN, "val" to Color.GREEN
    )

    fun setIdentifier(string: String) {
        if (!keyWordsColors.containsKey(string))
            keyWordsColors.put(string, Color.ORANGE)
    }

//    fun paintBrackets(row: Int, position: Int,bracket : Char, g: Graphics) {
//        //TODO Взял код из выделения, можно зарефакторить и вынести в одно место высчитывание координат и бокса по позиции
//        var pairBracket = bracketsService.getPairBracket(row,position,bracket)
//        if (pairBracket != null){
//            val g2 = g as Graphics2D
//            var metrics = g2.getFontMetrics(g2.font)
//            var char = bracket
//
//            var x = panel.borderX + metrics.stringWidth(panel.workspaceText[row].substring(0,position))
//            var y = panel.borderY + panel.lineSpacing * row - metrics.height + metrics.descent
//            var height = metrics.height
//            var width = metrics.charWidth(char)
//            var firstBracketRectangle = Rectangle2D.Double(x.toDouble(),y.toDouble(),width.toDouble(),height.toDouble())
//
//            x = panel.borderX + metrics.stringWidth(panel.workspaceText[pairBracket.row].substring(0,pairBracket.position))
//            y = panel.borderY + panel.lineSpacing * pairBracket.row - metrics.height + metrics.descent + 2
//            height = metrics.height
//            width = metrics.charWidth(char)
//            var secondBracketRectangle = Rectangle2D.Double(x.toDouble(),y.toDouble(),width.toDouble(),height.toDouble())
//
//            g2.color = Color.GREEN
//            g2.fill(firstBracketRectangle)
//            g2.fill(secondBracketRectangle)
//            g2.color = Color.BLACK
//        }
//    }
    private fun getXbyPosition(row : Int,position : Int,metrics : FontMetrics) : Int
    {
        return 0
    }
    private fun getYbyRow(row : Int,metrics : FontMetrics) : Int
    {
        return panel.lineSpacing * row
    }


    fun brackets(row: Int, position: Int,bracket : Char, g: Graphics?) { //запасной метод для перебора во время написания
        var fullText = panel .workspaceText
        if (fullText[row][position] == '{' /*|| fullText[row][position-1] == '{'*/) {
            var secondBracketRow = row
            var secondBracketPosition = position
            var counter = 1
            while (counter != 0) {
                if (fullText[secondBracketRow][secondBracketPosition] == '}') {
                    counter--
                    if (counter == 0)
                        break
                } else if (fullText[secondBracketRow][secondBracketPosition] == '{') {
                    counter++
                }
                if (secondBracketPosition == fullText[secondBracketRow].lastIndex) {
                    secondBracketRow++
                    secondBracketPosition = 0
                    if (secondBracketRow == fullText.lastIndex)
                        counter = 0
                } else
                    secondBracketPosition++
            }
        }
    }
}