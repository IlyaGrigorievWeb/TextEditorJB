package TextEditorJB.Services

import TextEditorJB.Components.TextPanel
import TextEditorJB.Entities.FileInfo
import TextEditorJB.Entities.SourceText
import java.io.*
import javax.swing.JFileChooser

class FileService (private val panel: TextPanel,private val workspaceService: WorkspaceService,private val fileInfo : FileInfo) {

    fun readPartial(lines : Int,sourceText : SourceText) {

        val file = fileInfo.openingFile
        if (file != null) {
            if ( file.exists()) {
                var i = 0
                val listString: MutableList<String> = mutableListOf()
                while (fileInfo.buffer!!.ready() && i < lines) {
                    val line = fileInfo.buffer!!.readLine()
                    listString.add(line)
                    i++
                }
                if (fileInfo.readerPosition == 0)
                    sourceText.text = listString
                else
                    sourceText.text.addAll(listString)
                fileInfo.readerPosition += i
            }
            else
                throw FileNotFoundException()
        }

    }

    fun open(sourceText : SourceText){


        sourceText.positionInRow = 0
        sourceText.activeRow = 0
        sourceText.text = mutableListOf("")

        panel.position = 0

        val fileChooser = JFileChooser()
        fileChooser.fileSelectionMode = JFileChooser.FILES_ONLY


        fileChooser.showOpenDialog(panel)
        val file = fileChooser.selectedFile

        if (file != null && file.exists()) {
            fileInfo.readerPosition = 0

            fileInfo.fileReader = FileReader(file)
            fileInfo.buffer = BufferedReader(fileInfo.fileReader!!)


            fileInfo.openingFile = file
            readPartial(panel.rowsInWorkspace*2,sourceText)
            workspaceService.setWorkspace()
            panel.repaint()
        }
        else
            throw FileNotFoundException()
    }

    fun save(sourceText : SourceText)
    {
        val fileChooser = JFileChooser()
        fileChooser.fileSelectionMode = JFileChooser.FILES_ONLY

        val file : File?

        if (fileInfo.openingFile == null) {

            fileChooser.showSaveDialog(panel)
            file = fileChooser.selectedFile
        }
        else{
            file = fileInfo.openingFile!!
        }

        if(file!!.exists()){

            val fileWriter = FileWriter(file)
            val bufferWriter = BufferedWriter(fileWriter)
            for(string in sourceText.text)
            {
                bufferWriter.write(string)
                bufferWriter.newLine()
            }

            bufferWriter.close()
        }
        else
            throw FileNotFoundException()
    }
}