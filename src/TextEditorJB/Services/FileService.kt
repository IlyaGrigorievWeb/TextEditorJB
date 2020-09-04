package TextEditorJB.Services

import TextEditorJB.Components.TextPanel
import java.io.*
import javax.swing.JFileChooser

class FileService (textPanel: TextPanel, workspaceService: WorkspaceService) {

    val workspaceService = workspaceService
    val panel = textPanel
    var activeFilePath = ""
    var readerPosition = 0

    fun getFileRows(rowsCount : Int){
        val file = MyForm.openingFile
        if (file!!.exists())
        {
            var fileReader = FileReader(file)
            var buffer = BufferedReader(fileReader)

            if (readerPosition!=0)
                buffer.skip((readerPosition-1).toLong())

            var i = 0
//            while(buffer.ready() && rowsCount > i)
//            {
//                workspaceService.sourceText[i] = buffer.readLine()
//                i++
//            }
            var listString : MutableList<String> = mutableListOf()
            while(buffer.ready())
            {
                listString.add(buffer.readLine())
                //textPanel.fullText[i] = buffer.readLine()
                //EnterAction().actionPerformed(e)
                i++
            }
            workspaceService.sourceText = listString.toTypedArray()

        }
    }

    fun open(){
        var textPanel = MyForm.panel as TextPanel
        workspaceService.position = 0

        var fileChooser = JFileChooser()
        fileChooser.fileSelectionMode = JFileChooser.FILES_ONLY


        fileChooser.showOpenDialog(MyForm.panel)
        var file = fileChooser.selectedFile

        if (file.exists()) {
            MyForm.openingFile = file
            getFileRows(panel.rowsInWorkspace*2)
            workspaceService.setWorkspace()
            panel.repaint()
        }
//            var fileReader = FileReader(file)
//            var buffer = BufferedReader(fileReader)
//
//            //panel.fullText[panel.activeRow] = buffer.readLine()
////            var i = 0
////            //textPanel.fullText = Array()
////            var listString : MutableList<String> = mutableListOf()
////            while(buffer.ready())
////            {
////                listString.add(buffer.readLine())
////                //textPanel.fullText[i] = buffer.readLine()
////                //EnterAction().actionPerformed(e)
////                i++
////            }
//            //textPanel.fullText = listString.toTypedArray()
//
//
//            MyForm.panel.repaint()
//        }
    }

    fun save()
    {
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

    fun getNextRow()
    {
        val file = MyForm.openingFile
        if (file!!.exists())
        {
            var fileReader = FileReader(file)
            var buffer = BufferedReader(fileReader)

            buffer.skip((readerPosition-1) as Long)
            if (buffer.ready())
                workspaceService.sourceText[readerPosition] = buffer.readLine()

        }
    }

}