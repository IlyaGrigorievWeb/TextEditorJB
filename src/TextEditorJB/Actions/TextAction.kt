package TextEditorJB.Actions

import TextEditorJB.Components.Caret
import TextEditorJB.Components.TextPanel
import java.awt.event.ActionEvent
import javax.swing.AbstractAction

class TextAction : AbstractAction() {
    override fun actionPerformed(e: ActionEvent?) {
        var actEvent = e as ActionEvent
        var a = actEvent.source as TextPanel
        //MyComponent.strText += actEvent.actionCommand


        //actEvent.actionCommand.toCharArray()[0].toByte()


//        when(actEvent.actionCommand.toCharArray()[0].toByte()){
//            /*delete*/127.toByte() -> println("delete") //нужна каретка
//            ///*space*/32.toByte() -> println(" ")
//            /*backspace*/8.toByte() -> MyPanel.strText = MyPanel.strText.removeRange(MyPanel.strText.lastIndex,MyPanel.strText.lastIndex+1)
//            /*characters*/else -> MyPanel.strText += actEvent.actionCommand
//        }
        var aqsw = TextPanel.strText
        if (actEvent.actionCommand == "\b") { //backspace
            if (a.caret.positionInRow >= 0) {
                TextPanel.strText = TextPanel.strText.removeRange(a.caret.positionInRow - 1, a.caret.positionInRow)
                LeftAction().actionPerformed(e)
            }
        }
        else if(actEvent.actionCommand.toCharArray()[0].toByte() == 127.toByte()){ //delete

            if (a.caret.positionInRow <= TextPanel.strText.length-1)
                TextPanel.strText = TextPanel.strText.removeRange(a.caret.positionInRow,a.caret.positionInRow+1)
//
//            var position = 0;
//            var step =0;
//            for (char in TextPanel.strText)
//            {
//                step = Caret.characterWidthMap[char.toString()]!!
//                position+=step
//
//                if (position >= a.caret.positionX)
//                {a.caret.rightWidth = step}
//
//
//            }
        }
        else if(false){

            TextPanel.strText += System.lineSeparator() //блок для enter и переноса строки
        }
        else{ //письменные символы
            var char = actEvent.actionCommand
            val charWidth = Caret.characterWidthMap[char]!!


            //TextPanel.strText += char;


            var sb = StringBuilder(TextPanel.strText)
            sb.insert(a.caret.positionInRow,char)
            TextPanel.strText = sb.toString()

            a.caret.positionX += charWidth
            a.caret.positionInRow++

            a.caret.leftWidth = charWidth
        }



        //a.graphics.clearRect(0,0,1000,1000)
        //a.repaint()
        //a.paintComponents(a.graphics)

        //a.graphics.drawString(MyComponent.strText,10,10)
        //println(a.components.count())

        a.paint(a.graphics)
        //a.repaint()

        //println(MyComponent.strText)
    }
}