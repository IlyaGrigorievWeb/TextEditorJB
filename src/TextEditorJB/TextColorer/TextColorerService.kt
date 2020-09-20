package TextEditorJB.TextColorer

import TextEditorJB.Components.TextPanel
import TextEditorJB.Entities.SourceText
import TextEditorJB.Services.TextSelectionService
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D

class TextColorerService (textPanel : TextPanel,val textSelectionService: TextSelectionService) {

    val panel = textPanel
    private var bracketsService = BracketsService(panel, panel.sourceText)
    fun paintKeyWords(string: String, graphics: Graphics, x: Int, y: Int) { //сколько скобок от начала столько скобок от конца

        val metrics = graphics.getFontMetrics(graphics.font)
        var str = ""

        val words = string.split(" ")
        for ((index, word) in words.withIndex()) {
            if (types.containsKey(word)) {
                setIdentifier(words[index + 1])
            }
            if (keyWordsColors.containsKey(word)) {
                graphics.color = keyWordsColors[word]
                graphics.drawString("$word ", x + metrics.stringWidth(str), y)
                graphics.color = Color.BLACK
            }
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

    private val keyWordsColors: MutableMap<String, Color> = mutableMapOf(
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
    private val types: MutableMap<String, Color> = mutableMapOf(
            "byte" to Color.BLUE, "short" to Color.BLUE, "int" to Color.BLUE, "long" to Color.BLUE,
            "char" to Color.BLUE, "float" to Color.BLUE, "double" to Color.BLUE, "boolean" to Color.BLUE,
            "var" to Color.GREEN, "val" to Color.GREEN
    )

    private fun setIdentifier(string: String) {
        if (!keyWordsColors.containsKey(string))
            keyWordsColors[string] = Color.ORANGE
    }

    fun paintBrackets(row: Int, position: Int,bracket : Char, g: Graphics,sourceText: SourceText) {
        val pairBracket = bracketsService.searchBracket(row,position,bracket)
        if (pairBracket != null){
            val g2 = g as Graphics2D

            val firstBracketRectangle = textSelectionService.getStringBox(sourceText,row,position-1,position)
            val secondBracketRectangle = textSelectionService.getStringBox(sourceText,pairBracket.row,pairBracket.position-1,pairBracket.position)

            g2.color = Color.GREEN
            g2.fill(firstBracketRectangle)
            g2.fill(secondBracketRectangle)
            g2.color = Color.BLACK
        }
    }
}