import TextEditorJB.Actions.LeftAction
import TextEditorJB.Actions.MouseListener
import TextEditorJB.Actions.RightAction
import TextEditorJB.Actions.TextAction
import TextEditorJB.Components.TextPanel
import java.awt.Font
import java.awt.event.KeyEvent
import javax.swing.AbstractAction
import javax.swing.ActionMap
import javax.swing.JComponent
import javax.swing.KeyStroke

fun main(args: Array<String>){

//    var awer = "qwqwe";
//    awer = awer.removeRange(awer.lastIndex,awer.lastIndex+1)
//    print(awer)
//    var strfont = GraphicsEnvironment.getLocalGraphicsEnvironment().availableFontFamilyNames
//    for (i in strfont)
//    {
//     println(i)
//    }


    var myFont: Font = Font("Calibri", Font.BOLD,20)
    MyForm.frame.add(MyForm.panel)
    MyForm.panel.add((MyForm.panel as TextPanel).caret)
    MyForm.panel.addMouseListener(MouseListener())
    var qwe = MyForm.frame
    //MyForm.panel.graphics.font = myFont


    val myAct : AbstractAction = TextAction()
    val RightAction : AbstractAction = RightAction()
    val LeftAction : AbstractAction = LeftAction()
    //MyForm.panel.add(MyComponent())



    val inpMap = MyForm.panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)

    val a = "QWERTYUIOPASDFGHJKLZXCVBNM"

    for (i in a){
        val ks : KeyStroke = KeyStroke.getKeyStroke("pressed "+i)
        inpMap.put(ks, "changeColor")
    }

    val ks3 : KeyStroke = KeyStroke.getKeyStroke("BACK_SPACE")
    inpMap.put(ks3, "changeColor")

    val ks4 : KeyStroke = KeyStroke.getKeyStroke("SPACE")
    inpMap.put(ks4, "changeColor")

    val ks5 : KeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0)
    inpMap.put(ks5, "changeColor")

    val ks6 : KeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0)
    inpMap.put(ks6, "pressRight")

    val ks7 : KeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0)
    inpMap.put(ks7, "pressLeft")

    val ks8 : KeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0)
    inpMap.put(ks8, "changeColor")

    //val ks : KeyStroke = KeyStroke.getKeyStroke("ctrl B")
    //val inpMap = MyForm.panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
    //inpMap.put(ks, "changeColor")

    val actMap : ActionMap = MyForm.panel.actionMap

    actMap.put("changeColor", myAct)

    actMap.put("pressRight", RightAction)

    actMap.put("pressLeft", LeftAction)

    MyForm.frame.repaint()

}