package TextEditorJB.Tests

import TextEditorJB.Components.TextPanel
import TextEditorJB.Entities.SourceText
import TextEditorJB.Services.NavigationService
import org.junit.Before
import org.junit.Test

class NavigationServiceTest {

    val sourceText = SourceText()
    val panel = TextPanel(sourceText)
    val navigationService = NavigationService(panel)

    @Before
    fun setUp() {
        sourceText.text = mutableListOf("qwerty","qwerty", "qwerty")
        sourceText.activeRow = 1
        sourceText.positionInRow = 3
    }

    @Test
    fun up() {
        navigationService.up(sourceText)
        assert(sourceText.activeRow == 0 && sourceText.positionInRow == 3)
    }

    @Test
    fun down() {
        navigationService.down(sourceText)
        assert(sourceText.activeRow == 2 && sourceText.positionInRow == 3)
    }

    @Test
    fun left() {
        navigationService.left(sourceText)
        assert(sourceText.activeRow == 1 && sourceText.positionInRow == 2)
    }

    @Test
    fun right() {
        navigationService.right(sourceText)
        assert(sourceText.activeRow == 1 && sourceText.positionInRow == 4)
    }

    @Test
    fun home() {
        navigationService.home(sourceText)
        assert(sourceText.activeRow == 1 && sourceText.positionInRow == 0)
    }

    @Test
    fun end() {
        navigationService.end(sourceText)
        assert(sourceText.activeRow == 1 && sourceText.positionInRow == 6)
    }
}