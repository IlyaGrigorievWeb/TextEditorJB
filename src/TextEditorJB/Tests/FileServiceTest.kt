package TextEditorJB.Tests

import TextEditorJB.Components.TextPanel
import TextEditorJB.Entities.SourceText
import TextEditorJB.Services.FileService
import TextEditorJB.Services.WorkspaceService
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.*


class FileServiceTest {

    val sourceText = SourceText()
    val panel = TextPanel(sourceText)
    val workspaceService = WorkspaceService(panel,sourceText)
    val fileService = FileService(panel,sourceText,workspaceService)
    var file : File? = null
    var text = mutableListOf<String>()

    @Before
    fun setUp() {
        file = File("file.txt")
        var fileWriter = FileWriter(file)
        var bufferWriter = BufferedWriter(fileWriter)
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

            fileService.openingFile = file
            fileService.fileReader = FileReader(file)
            fileService.buffer = BufferedReader(fileService.fileReader)
            fileService.setSourceText(5)
            fileService.buffer!!.close()
            Assert.assertArrayEquals(text.toTypedArray(),sourceText.text)

    }

    @Test
    fun save() {

            fileService.openingFile = file
            sourceText.text = arrayOf("qwer")
            fileService.save()
            fileService.fileReader = FileReader(file)
            fileService.buffer = BufferedReader(fileService.fileReader)
            assert(fileService.buffer!!.readLine() == "qwer")
            fileService.buffer!!.close()


    }
}