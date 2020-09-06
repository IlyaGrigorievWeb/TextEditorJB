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

    var fileReader : FileReader? = null
    var buffer : BufferedReader? = null


    fun setSourceText(rowsCount : Int) {

        val file = AppForm.openingFile
        if (file != null && file!!.exists()) {

//            if (readerPosition!=0)
//                buffer.skip((readerPosition-1).toLong())

            var i = 0
            var listString: MutableList<String> = mutableListOf()
            while (buffer!!.ready() && i < rowsCount) {
                val line = buffer!!.readLine()
                listString.add(line)
                i++
                for ((index, char) in line.withIndex()) {
                    if (char == '{' || char == '}')
                        panel.coloringService.bracketsService.addBracket(readerPosition + i - 1, index + 1, char)
                }
            }
            val arrayStrings = listString.toTypedArray()
            if (readerPosition == 0)
                sourceText.text = arrayStrings
            else
                sourceText.text += arrayStrings
            readerPosition += i
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
        readerPosition = 0


        fileReader = FileReader(file)
        buffer = BufferedReader(fileReader)


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
        var bufferWriter = BufferedWriter(fileWriter)
        for(string in sourceText.text)
        {
            bufferWriter.write(string)
            bufferWriter.newLine()
        }

        bufferWriter.close()
    }
}