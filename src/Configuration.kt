import TextEditorJB.Actions.*
import TextEditorJB.Components.TextPanel
import java.awt.Font
import java.awt.event.KeyEvent
import javax.swing.*

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
    MyForm.panel.addMouseListener(CustomMouseListener())
    //MyForm.frame.jMenuBar(jMenuBar)
    //MyForm.panel.graphics.font = myFont


    val myAct : AbstractAction = TextAction()
    val RightAction : AbstractAction = RightAction()
    val LeftAction : AbstractAction = LeftAction()
    val ShiftLeft : AbstractAction = ShiftLeft()
    val ShiftRight : AbstractAction = ShiftRight()
    val CopyAction : AbstractAction = CopyAction()
    val PasteAction : AbstractAction = PasteAction()
    val EnterAction : AbstractAction = EnterAction()
    //MyForm.panel.add(MyComponent())



    val inpMap = MyForm.panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)

    val a = "QWERTYUIOPASDFGHJKLZXCVBNM"

   // for (i in a){
     //   val ks : KeyStroke = KeyStroke.getKeyStroke("pressed "+i)
     //  inpMap.put(ks, "changeColor")
    //}

    val ks3 : KeyStroke = KeyStroke.getKeyStroke("BACK_SPACE")
    inpMap.put(ks3, "changeColor")

    val ks4 : KeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_TAB,0)
    inpMap.put(ks4, "changeColor")

    val ks5 : KeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0)
    inpMap.put(ks5, "changeColor")

    val ks6 : KeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0)
    inpMap.put(ks6, "pressRight")

    val ks7 : KeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0)
    inpMap.put(ks7, "pressLeft")

    val ks8 : KeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0)
    inpMap.put(ks8, "changeColor")

    val ks9 : KeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,KeyEvent.SHIFT_DOWN_MASK)
    inpMap.put(ks9, "shiftLeft")

    val ks10 : KeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,KeyEvent.SHIFT_DOWN_MASK)
    inpMap.put(ks10, "shiftRight")

    val ks11 : KeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_C,KeyEvent.CTRL_DOWN_MASK)
    inpMap.put(ks11, "copyBuffer")
    val ks12 : KeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_V,KeyEvent.CTRL_DOWN_MASK)
    inpMap.put(ks12, "pasteText")
    val ks13 : KeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0)
    inpMap.put(ks13, "enter")

    //val ks : KeyStroke = KeyStroke.getKeyStroke("ctrl B")
    //val inpMap = MyForm.panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
    //inpMap.put(ks, "changeColor")

    val actMap : ActionMap = MyForm.panel.actionMap

    actMap.put("changeColor", myAct)

    actMap.put("pressRight", RightAction)

    actMap.put("pressLeft", LeftAction)

    actMap.put("shiftLeft", ShiftLeft)

    actMap.put("shiftRight", ShiftRight)

    actMap.put("copyBuffer", CopyAction)

    actMap.put("pasteText", PasteAction)

    actMap.put("enter", EnterAction)

    MyForm.frame.repaint()
    MyForm.panel.revalidate()



//    var ar : Array<Int> = arrayOf(10,30)
//    ar = ar.plusElement(40)
//    for (i in ar)
//        print(i)
}