package TextEditorJB.Tests

import TextEditorJB.Components.TextPanel
import TextEditorJB.Entities.SourceText
import TextEditorJB.Services.NavigationService
import TextEditorJB.Services.TextSelectionService
import TextEditorJB.Services.TextService
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class TextServiceTest {


    val sourceText = SourceText()
    val panel = TextPanel(sourceText)
    val navigationService = NavigationService(panel,sourceText)
    val textService = TextService(panel,sourceText,navigationService)

    @Before
    fun setUp() {
        sourceText.text = arrayOf("qwerty","qwerty", "qwerty")
        sourceText.activeRow = 1
        sourceText.positionInRow = 3
    }

    @Test
    fun enter() {
        textService.enter()
        var resText = arrayOf("qwerty","qwe","rty","qwerty")
        assertArrayEquals(sourceText.text,resText)
    }

    @Test
    fun backspace() {
        textService.backspace()
        var resText = arrayOf("qwerty","qwrty","qwerty")
        assertArrayEquals(sourceText.text,resText)
    }

    @Test
    fun delete() {
        textService.delete()
        var resText = arrayOf("qwerty","qwety","qwerty")
        assertArrayEquals(sourceText.text,resText)
    }

    @Test
    fun tab() {
        textService.tab()
        var resText = arrayOf("qwerty","qwe    rty","qwerty")
        assertArrayEquals(sourceText.text,resText)
    }
}