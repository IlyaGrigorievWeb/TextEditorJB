package TextEditorJB.Tests

import TextEditorJB.Components.TextPanel
import TextEditorJB.Entities.SourceText
import TextEditorJB.Services.FileService
import TextEditorJB.Services.NavigationService
import TextEditorJB.Services.TextSelectionService
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class TextSelectionServiceTest {

    val sourceText = SourceText()
    val panel = TextPanel(sourceText)
    val fileService = FileService(panel,panel.workspaceService,sourceText)
    val navigationService = NavigationService(panel,sourceText,fileService)
    val textSelectionService = TextSelectionService(panel,navigationService,sourceText)

    @Before
    fun setUp() {
        sourceText.text = arrayOf("qwert","werwert","dsafasdfasdf")
        sourceText.activeRow = 1
        sourceText.positionInRow = 3
    }

    @After
    fun tearDown() {
    }

    @Test
    fun shiftLeft() {

    }

    @Test
    fun shiftRight() {

        textSelectionService.shiftRight()
        val trueResult = sourceText.activeRow == 1 && sourceText.positionInRow == 4 &&
                textSelectionService.textSelection.selectingStartRow == 1 && textSelectionService.textSelection.selectingEndRow == 1 &&
                textSelectionService.textSelection.selectingStartChar == 3 && textSelectionService.textSelection.selectingEndChar == 4
        assertTrue(trueResult)

    }

    @Test
    fun shiftUp() {
    }

    @Test
    fun shiftDown() {
    }

    @Test
    fun shiftClick() {
        assertFalse(true)
    }

    @Test
    fun getSelected() {
    }
}