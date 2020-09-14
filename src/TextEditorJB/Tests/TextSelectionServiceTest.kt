package TextEditorJB.Tests

import TextEditorJB.Components.TextPanel
import TextEditorJB.Entities.SourceText
import TextEditorJB.Services.NavigationService
import TextEditorJB.Services.TextSelectionService
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class TextSelectionServiceTest {

    val sourceText = SourceText()
    val panel = TextPanel(sourceText)
    val navigationService = NavigationService(panel)
    val textSelectionService = TextSelectionService(panel,navigationService)

    @Before
    fun setUp() {
        sourceText.text = mutableListOf("qwert","werwert","dsafasdfasdf")
        sourceText.activeRow = 1
        sourceText.positionInRow = 3
    }

    @Test
    fun shiftLeft() {

        textSelectionService.shiftLeft(sourceText)
        assertTrue(sourceText.activeRow == 1 && sourceText.positionInRow == 2)

        assertTrue(textSelectionService.textSelection.selectingStartRow == 1 && textSelectionService.textSelection.selectingEndRow == 1)
        assertTrue(textSelectionService.textSelection.selectingStartChar == 2 && textSelectionService.textSelection.selectingEndChar == 3)
    }

    @Test
    fun shiftRight() {

        textSelectionService.shiftRight(sourceText)
        assertTrue(sourceText.activeRow == 1 && sourceText.positionInRow == 4)

        assertTrue(textSelectionService.textSelection.selectingStartRow == 1 && textSelectionService.textSelection.selectingEndRow == 1)
        assertTrue(textSelectionService.textSelection.selectingStartChar == 3 && textSelectionService.textSelection.selectingEndChar == 4)

    }

    @Test
    fun shiftUp() {
        textSelectionService.shiftUp(sourceText)
        assertTrue(sourceText.activeRow == 0 && sourceText.positionInRow == 3)

        assertTrue(textSelectionService.textSelection.selectingStartRow == 0 && textSelectionService.textSelection.selectingEndRow == 1)
        assertTrue(textSelectionService.textSelection.selectingStartChar == 3 && textSelectionService.textSelection.selectingEndChar == 3)
    }

    @Test
    fun shiftDown() {
        textSelectionService.shiftDown(sourceText)

        assertTrue(sourceText.activeRow == 2 && sourceText.positionInRow == 3)
        assertTrue(textSelectionService.textSelection.selectingStartRow == 1 && textSelectionService.textSelection.selectingEndRow == 2)
        assertTrue(textSelectionService.textSelection.selectingStartChar == 3 && textSelectionService.textSelection.selectingEndChar == 3)
    }
}