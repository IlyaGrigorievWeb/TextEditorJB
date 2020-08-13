package TextEditorJB.FileService

import TextEditorJB.Actions.EnterAction
import TextEditorJB.Components.TextPanel
import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.DataFlavor
import java.awt.event.ActionEvent
import java.awt.event.KeyEvent
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import java.lang.StringBuilder
import javax.swing.*

fun getConfiguredMenu() : JMenuBar
{
    var jMenuBar  = JMenuBar()

    val fileOption = JMenu("File")
    jMenuBar.add(fileOption)

    val open = JMenuItem("Open",0)
    val save = JMenuItem("Save",0)

    fileOption.add(open)
    fileOption.add(save)

    open.addActionListener(OpenAction())
//    save.addActionListener {
//        println("saved")
//    }
    save.addActionListener(SaveAction())
    save.accelerator = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK)

    return jMenuBar
}

class OpenAction : AbstractAction() {

    override fun actionPerformed(e: ActionEvent?) {
        var actEvent = e as ActionEvent
        var panel = actEvent.source as TextPanel

        var fileChooser = JFileChooser()
        fileChooser.fileSelectionMode = JFileChooser.FILES_ONLY


        fileChooser.showOpenDialog(MyForm.panel)
        var file = fileChooser.selectedFile

        if (file.exists())
        {
            MyForm.openingFile = file
            var fileReader = FileReader(file)
            var buffer = BufferedReader(fileReader)

            //panel.fullText[panel.activeRow] = buffer.readLine()
            while(buffer.ready())
            {
                panel.fullText[panel.activeRow] = buffer.readLine()
                EnterAction().actionPerformed(e)
            }
            MyForm.panel.repaint()
        }


    }
}

class SaveAction : AbstractAction() {

    override fun actionPerformed(e: ActionEvent?) {
        var fileChooser = JFileChooser()
        fileChooser.fileSelectionMode = JFileChooser.FILES_ONLY


        if (MyForm.openingFile == null) {

            fileChooser.showSaveDialog(MyForm.panel)

        }
        else{

            val file = MyForm.openingFile

        }



    }
}