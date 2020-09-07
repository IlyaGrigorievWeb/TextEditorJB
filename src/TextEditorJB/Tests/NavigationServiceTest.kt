package TextEditorJB.Tests

import TextEditorJB.Components.TextPanel
import TextEditorJB.Entities.SourceText
import TextEditorJB.Services.NavigationService
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class NavigationServiceTest {

    val sourceText = SourceText()
    val panel = TextPanel(sourceText)
    val navigationService = NavigationService(panel,sourceText)

    @Before
    fun setUp() {
        sourceText.text = arrayOf("qwerty","qwerty", "qwerty")
        sourceText.activeRow = 1
        sourceText.positionInRow = 3
    }

    @Test
    fun up() {
        navigationService.Up()
        assert(sourceText.activeRow == 0 && sourceText.positionInRow == 3)
    }

    @Test
    fun down() {
        navigationService.Down()
        assert(sourceText.activeRow == 2 && sourceText.positionInRow == 3)
    }

    @Test
    fun left() {
        navigationService.Left()
        assert(sourceText.activeRow == 1 && sourceText.positionInRow == 2)
    }

    @Test
    fun right() {
        navigationService.Right()
        assert(sourceText.activeRow == 1 && sourceText.positionInRow == 4)
    }

    @Test
    fun home() {
        navigationService.Home()
        assert(sourceText.activeRow == 1 && sourceText.positionInRow == 0)
    }

    @Test
    fun end() {
        navigationService.End()
        assert(sourceText.activeRow == 1 && sourceText.positionInRow == 6)
    }
}