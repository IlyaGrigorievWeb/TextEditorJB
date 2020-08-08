package TextEditorJB.Actions

import MyForm
import TextEditorJB.Components.Caret
import TextEditorJB.Components.TextPanel
import java.awt.Dimension
import java.awt.Font
import java.awt.SystemColor.text
import java.awt.event.ActionEvent
import javax.swing.AbstractAction


class TextAction : AbstractAction() {
    override fun actionPerformed(e: ActionEvent?) {
        var actEvent = e as ActionEvent
        var panel = actEvent.source as TextPanel
        //MyComponent.textRow += actEvent.actionCommand


        //actEvent.actionCommand.toCharArray()[0].toByte()


//        when(actEvent.actionCommand.toCharArray()[0].toByte()){
//            /*delete*/127.toByte() -> println("delete") //нужна каретка
//            ///*space*/32.toByte() -> println(" ")
//            /*backspace*/8.toByte() -> MyPanel.textRow = MyPanel.textRow.removeRange(MyPanel.textRow.lastIndex,MyPanel.textRow.lastIndex+1)
//            /*characters*/else -> MyPanel.textRow += actEvent.actionCommand
//        }

        if (actEvent.actionCommand == "\b") { //backspace
            if (panel.caret.positionInRow >= 0) {

                panel.caret.moveLeft()
                TextPanel.textRow = TextPanel.textRow.removeRange(panel.caret.positionInRow, panel.caret.positionInRow+1)
            }
        }
        else if(actEvent.actionCommand.toCharArray()[0].toByte() == 127.toByte()){ //delete

            if (panel.caret.positionInRow <= TextPanel.textRow.length-1)
                TextPanel.textRow = TextPanel.textRow.removeRange(panel.caret.positionInRow,panel.caret.positionInRow+1)
        }
        else if(false){

            TextPanel.textRow += System.lineSeparator() //блок для enter и переноса строки
        }
        else{ //письменные символы
            var char = actEvent.actionCommand

            var sb = StringBuilder(TextPanel.textRow)
            sb.insert(panel.caret.positionInRow,char)
            TextPanel.textRow = sb.toString()

            panel.caret.positionInRow++

            //a.caret.leftWidth = charWidth
            var font = Font("Calibri",0,20)
            val metrics = panel.getFontMetrics(font)
            val width = metrics.stringWidth(char)

            println(metrics.stringWidth(TextPanel.textRow))
            panel.caret.positionX += width
            println((MyForm.panel as TextPanel).caret.positionX)
        }

        panel.paint(panel.graphics)
        //a.repaint()

        //println(MyComponent.textRow)
    }
}