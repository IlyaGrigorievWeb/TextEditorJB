package TextEditorJB.TextColoringService

import TextEditorJB.Components.TextPanel
import java.awt.Color
import java.awt.Graphics

class TextColoringService
{
    fun paintKeyWords (string : String, graphics : Graphics, x :Int,y: Int){
        //var graphics = panel.graphics
        val metrics = graphics.getFontMetrics(graphics.font)
        var str = ""

            var words = string.split(" ")
            for (word in words){
                if (keyWordsColors.containsKey(word)){
                    graphics.color = keyWordsColors[word]
                    graphics.drawString("$word ",x+metrics.stringWidth(str),y)
                    graphics.color = Color.BLACK
                }
                else{
                    graphics.drawString("$word ",x+metrics.stringWidth(str),y)
                }
                str += "$word "
        }
    }
    val keyWordsColors : Map<String, Color> = mapOf(
            "byte" to Color.BLUE, "short" to Color.BLUE, "int" to Color.BLUE , "long" to Color.BLUE,
            "char" to Color.BLUE, "float" to Color.BLUE, "double" to Color.BLUE , "boolean" to Color.BLUE,
            "if" to Color.GREEN, "else" to Color.GREEN, "switch" to Color.GREEN , "case" to Color.BLUE,
            "default" to Color.BLUE, "while" to Color.BLUE, "do" to Color.BLUE , "break" to Color.BLUE,
            "continue" to Color.BLUE, "for" to Color.BLUE, "try" to Color.BLUE , "catch" to Color.BLUE,
            "finally" to Color.BLUE, "throw" to Color.BLUE, "throws" to Color.BLUE , "private" to Color.BLUE,
            "protected" to Color.BLUE, "public" to Color.BLUE, "import" to Color.BLUE , "package" to Color.BLUE,
            "class" to Color.BLUE, "interface" to Color.BLUE, "extends" to Color.BLUE , "implements" to Color.BLUE,
            "static" to Color.BLUE, "final" to Color.BLUE, "void" to Color.BLUE , "abstract" to Color.BLUE,
            "new" to Color.BLUE, "return" to Color.BLUE, "this" to Color.BLUE , "super" to Color.BLUE,
            "synchronized" to Color.BLUE, "volatile" to Color.BLUE, "const" to Color.BLUE , "goto" to Color.BLUE,
            "instanceof" to Color.BLUE, "enum" to Color.BLUE, "assert" to Color.BLUE , "transient" to Color.BLUE,
            "strictfp" to Color.BLUE
            )
}