package TextEditorJB.Tests

import TextEditorJB.Components.TextPanel
import TextEditorJB.Entities.SourceText
import TextEditorJB.Services.NavigationService
import TextEditorJB.Services.TextService
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class TextServiceTest {


    val sourceText = SourceText()
    val panel = TextPanel(sourceText)
    val navigationService = NavigationService(panel)
    val textService = TextService(panel,navigationService)

    @Before
    fun setUp() {
        sourceText.text = mutableListOf("qwerty","qwerty", "qwerty")
        sourceText.activeRow = 1
        sourceText.positionInRow = 3
    }

    @Test
    fun enter() {
        textService.enter(sourceText)
        val resText = mutableListOf("qwerty","qwe","rty","qwerty")
        assertTrue(sourceText.text == resText)
    }

    @Test
    fun backspace() {
        textService.backspace(sourceText)
        val resText = mutableListOf("qwerty","qwrty","qwerty")
        assertTrue(sourceText.text == resText)
    }

    @Test
    fun delete() {
        textService.delete(sourceText)
        val resText = mutableListOf("qwerty","qwety","qwerty")
        assertTrue(sourceText.text == resText)
    }

    @Test
    fun tab() {
        textService.tab(sourceText)
        val resText = mutableListOf("qwerty","qwe    rty","qwerty")
        assertTrue(sourceText.text == resText)
    }
}