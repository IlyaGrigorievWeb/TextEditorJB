package TextEditorJB.Tests

import TextEditorJB.Components.TextPanel
import TextEditorJB.Entities.SourceText
import TextEditorJB.Services.NavigationService
import org.junit.Before
import org.junit.Test

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
        navigationService.up()
        assert(sourceText.activeRow == 0 && sourceText.positionInRow == 3)
    }

    @Test
    fun down() {
        navigationService.down()
        assert(sourceText.activeRow == 2 && sourceText.positionInRow == 3)
    }

    @Test
    fun left() {
        navigationService.left()
        assert(sourceText.activeRow == 1 && sourceText.positionInRow == 2)
    }

    @Test
    fun right() {
        navigationService.right()
        assert(sourceText.activeRow == 1 && sourceText.positionInRow == 4)
    }

    @Test
    fun home() {
        navigationService.home()
        assert(sourceText.activeRow == 1 && sourceText.positionInRow == 0)
    }

    @Test
    fun end() {
        navigationService.end()
        assert(sourceText.activeRow == 1 && sourceText.positionInRow == 6)
    }
}