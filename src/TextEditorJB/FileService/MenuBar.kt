package TextEditorJB.FileService

import TextEditorJB.Actions.EnterAction
import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.DataFlavor
import java.awt.event.ActionEvent
import java.awt.event.KeyEvent
import java.io.*
import java.lang.StringBuilder
import javax.swing.*
import TextEditorJB.Components.TextPanel as TextPanel

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
        //var actEvent = e as ActionEvent
        var textPanel = MyForm.panel as TextPanel

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
            var i = 0
            //textPanel.fullText = Array()
            var listString : MutableList<String> = mutableListOf()
            while(buffer.ready())
            {
                listString.add(buffer.readLine())
                //textPanel.fullText[i] = buffer.readLine()
                //EnterAction().actionPerformed(e)
                i++
            }
            textPanel.fullText = listString.toTypedArray()
            MyForm.panel.repaint()
        }


    }
}

class SaveAction : AbstractAction() {

    override fun actionPerformed(e: ActionEvent?) {
        var fileChooser = JFileChooser()
        fileChooser.fileSelectionMode = JFileChooser.FILES_ONLY
        var textPanel = MyForm.panel as TextPanel
        var file : File

        if (MyForm.openingFile == null) {

            fileChooser.showSaveDialog(MyForm.panel)
            file = fileChooser.selectedFile
        }
        else{
            file = MyForm.openingFile!!
        }
        var fileWriter = FileWriter(file)
        var buffer = BufferedWriter(fileWriter)
        for(string in textPanel.fullText)
        {
            buffer.write(string)
            buffer.newLine()
        }

        buffer.close()
    }
}