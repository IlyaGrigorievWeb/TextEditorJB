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
    val navigationService = NavigationService(panel,sourceText)
    val textSelectionService = TextSelectionService(panel,navigationService,sourceText)

    @Before
    fun setUp() {
        sourceText.text = arrayOf("qwert","werwert","dsafasdfasdf")
        sourceText.activeRow = 1
        sourceText.positionInRow = 3
    }

    @Test
    fun shiftLeft() {

        textSelectionService.shiftLeft()
        val trueResult = sourceText.activeRow == 1 && sourceText.positionInRow == 2 &&
                textSelectionService.textSelection.selectingStartRow == 1 && textSelectionService.textSelection.selectingEndRow == 1 &&
                textSelectionService.textSelection.selectingStartChar == 2 && textSelectionService.textSelection.selectingEndChar == 3
        assertTrue(trueResult)
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
        textSelectionService.shiftUp()
        val trueResult = sourceText.activeRow == 0 && sourceText.positionInRow == 3 &&
                textSelectionService.textSelection.selectingStartRow == 0 && textSelectionService.textSelection.selectingEndRow == 1 &&
                textSelectionService.textSelection.selectingStartChar == 3 && textSelectionService.textSelection.selectingEndChar == 3
        assertTrue(trueResult)
    }

    @Test
    fun shiftDown() {
        textSelectionService.shiftDown()
        val trueResult = sourceText.activeRow == 2 && sourceText.positionInRow == 3 &&
                textSelectionService.textSelection.selectingStartRow == 1 && textSelectionService.textSelection.selectingEndRow == 2 &&
                textSelectionService.textSelection.selectingStartChar == 3 && textSelectionService.textSelection.selectingEndChar == 3
        assertTrue(trueResult)
    }
}