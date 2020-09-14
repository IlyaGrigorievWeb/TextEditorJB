package TextEditorJB.Tests

import TextEditorJB.Components.TextPanel
import TextEditorJB.Entities.FileInfo
import TextEditorJB.Entities.SourceText
import TextEditorJB.Services.FileService
import TextEditorJB.Services.WorkspaceService
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.*


class FileServiceTest {

    val sourceText = SourceText()
    val panel = TextPanel(sourceText)
    val workspaceService = WorkspaceService(panel,sourceText)
    var file : File? = null
    var text = mutableListOf<String>()

    @Before
    fun setUp() {
        file = File("file.txt")
        val fileWriter = FileWriter(file!!)
        val bufferWriter = BufferedWriter(fileWriter)
        for (i in 0..4)
        {
            text.add("qwerty")
            bufferWriter.write("qwerty")
            bufferWriter.newLine()
        }
        bufferWriter.write("zxc")
        bufferWriter.close()
    }

    @After
    fun tearDown() {
        file!!.delete()
    }

    @Test
    fun setSourceText() {
        val fileInfo = FileInfo()
        fileInfo.openingFile = file
        fileInfo.fileReader = FileReader(file!!)
        fileInfo.buffer = BufferedReader(fileInfo.fileReader!!)
        val fileService = FileService(panel,workspaceService, fileInfo)
        fileService.readPartial(5,sourceText)
        fileInfo.buffer!!.close()
        assert(text.containsAll(sourceText.text))

    }

    @Test
    fun save() {
        val fileInfo = FileInfo()
        fileInfo.openingFile = file
        fileInfo.fileReader = FileReader(file!!)
        fileInfo.buffer = BufferedReader(fileInfo.fileReader!!)
        sourceText.text = mutableListOf("qwer")
        val fileService = FileService(panel,workspaceService, fileInfo)
        fileService.save(sourceText)
        assert(fileInfo.buffer!!.readLine() == "qwer")
        fileInfo.buffer!!.close()


    }
}