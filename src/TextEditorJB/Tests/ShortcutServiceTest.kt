package TextEditorJB.Tests

import TextEditorJB.Components.TextPanel
import TextEditorJB.Entities.SourceText
import TextEditorJB.Services.NavigationService
import TextEditorJB.Services.ShortcutService
import TextEditorJB.Services.TextSelectionService
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.StringSelection

class ShortcutServiceTest {

    val sourceText = SourceText()
    val panel = TextPanel(sourceText)
    val navigationService = NavigationService(panel)
    val textSelectionService = TextSelectionService(panel,navigationService)
    val shortcutService = ShortcutService(textSelectionService)

    @Before
    fun setUp() {
        sourceText.text = mutableListOf("qwerty","qwerty", "qwerty")
        sourceText.activeRow = 1
        sourceText.positionInRow = 3

    }

    @Test
    fun copy() {
        textSelectionService.textSelection.selectingStartRow = 0
        textSelectionService.textSelection.selectingStartChar = 0
        textSelectionService.textSelection.selectingEndRow = 1
        textSelectionService.textSelection.selectingEndChar = 2

        shortcutService.copy(sourceText)

        val clipboard : Clipboard = Toolkit.getDefaultToolkit().systemClipboard
        val dataFlavor : DataFlavor = DataFlavor.stringFlavor

        val string = clipboard.getData(dataFlavor).toString()

        assert(string == "qwerty\nqw")
    }

    @Test
    fun paste() {
        val clipboard : Clipboard = Toolkit.getDefaultToolkit().systemClipboard
        val stringSelection = StringSelection("abc")
        clipboard.setContents(stringSelection,null)

        shortcutService.paste(sourceText)

        assertTrue(sourceText.text == mutableListOf("qwerty","qweabcrty","qwerty"))
    }
}