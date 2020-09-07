package TextEditorJB.Tests

import TextEditorJB.Components.TextPanel
import TextEditorJB.Entities.SourceText
import TextEditorJB.Services.NavigationService
import TextEditorJB.Services.TextSelectionService
import TextEditorJB.Services.WorkspaceService
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class WorkspaceServiceTest {

    val sourceText = SourceText()
    val panel = TextPanel(sourceText)
    val workspaceService = WorkspaceService(panel,sourceText)

    @Before
    fun setUp() {
        sourceText.text = arrayOf("qwerty","qwerty", "qwerty")
        sourceText.activeRow = 0
        sourceText.positionInRow = 3
    }

    @Test
    fun setWorkspace() {
        panel.workspaceService.position = 1 //TODO применить либу и возвращать у panel размер окна на заглушках
        workspaceService.setWorkspace()
        //assertArrayEquals(panel.workspaceText,arrayOf(""))
    }
}