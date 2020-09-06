package TextEditorJB.Services

import TextEditorJB.Components.TextPanel
import TextEditorJB.Entities.SourceText
import java.io.*
import javax.swing.JFileChooser

class FileService (textPanel: TextPanel,workspaceService: WorkspaceService,sourceText: SourceText) {

    val workspaceService = workspaceService
    val sourceText = sourceText
    val panel = textPanel
    var readerPosition = 0


    fun setSourceText(rowsCount : Int){

        val file = AppForm.openingFile
        if (file!= null && file!!.exists())
        {
            var fileReader = FileReader(file)
            var buffer = BufferedReader(fileReader)

            if (readerPosition!=0)
                buffer.skip((readerPosition-1).toLong())

            var i = 0
            var listString : MutableList<String> = mutableListOf()
            while(buffer.ready() && i < rowsCount)
            {
                listString.add(buffer.readLine())
                i++
            }
            readerPosition += i
            sourceText.text += listString.toTypedArray()
        }
    }

    fun open(){
        sourceText.positionInRow = 0
        sourceText.activeRow = 0
        sourceText.text = arrayOf("")

        workspaceService.position = 0

        var fileChooser = JFileChooser()
        fileChooser.fileSelectionMode = JFileChooser.FILES_ONLY


        fileChooser.showOpenDialog(panel)
        var file = fileChooser.selectedFile

        if (file.exists()) {
            AppForm.openingFile = file
            setSourceText(panel.rowsInWorkspace*2)
            workspaceService.setWorkspace()
            panel.repaint()
        }
    }

    fun save()
    {
        var fileChooser = JFileChooser()
        fileChooser.fileSelectionMode = JFileChooser.FILES_ONLY
        var textPanel = panel
        var file : File

        if (AppForm.openingFile == null) {

            fileChooser.showSaveDialog(panel)
            file = fileChooser.selectedFile
        }
        else{
            file = AppForm.openingFile!!
        }
        var fileWriter = FileWriter(file)
        var buffer = BufferedWriter(fileWriter)
        for(string in sourceText.text)
        {
            buffer.write(string)
            buffer.newLine()
        }

        buffer.close()
    }

//    fun getFileText(rowsCount : Int) : Array<String>
//    {
//        val file = AppForm.openingFile
//        if (file!!.exists())
//        {
//            var fileReader = FileReader(file)
//            var buffer = BufferedReader(fileReader)
//
//            return Get
//            //buffer.skip((readerPosition-1) as Long)
////            if (buffer.ready())
////               sourceText.text[readerPosition] = buffer.readLine()
//
//        }
//    }

}