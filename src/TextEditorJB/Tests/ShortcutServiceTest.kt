package TextEditorJB.Tests

import TextEditorJB.Components.TextPanel
import TextEditorJB.Entities.SourceText
import TextEditorJB.Services.NavigationService
import TextEditorJB.Services.ShortcutService
import TextEditorJB.Services.TextSelectionService
import org.junit.After
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
    val navigationService = NavigationService(panel,sourceText)
    val textSelectionService = TextSelectionService(panel,navigationService,sourceText)
    val shortcutService = ShortcutService(panel,textSelectionService,sourceText)

    @Before
    fun setUp() {
        sourceText.text = arrayOf("qwerty","qwerty", "qwerty")
        sourceText.activeRow = 1
        sourceText.positionInRow = 3

    }

    @Test
    fun copy() {
        textSelectionService.textSelection.selectingStartRow = 0
        textSelectionService.textSelection.selectingStartChar = 0
        textSelectionService.textSelection.selectingEndRow = 1
        textSelectionService.textSelection.selectingEndChar = 2

        shortcutService.copy()

        var clipboard : Clipboard = Toolkit.getDefaultToolkit().systemClipboard
        var dataFlavor : DataFlavor = DataFlavor.stringFlavor

        var string = clipboard.getData(dataFlavor).toString()

        assert(string == "qwerty\nqw")
    }

    @Test
    fun paste() {
        var clipboard : Clipboard = Toolkit.getDefaultToolkit().systemClipboard
        var stringSelection = StringSelection("abc")
        clipboard.setContents(stringSelection,null)

        shortcutService.paste()

        assertArrayEquals(sourceText.text, arrayOf("qwerty","qweabcrty","qwerty"))
    }
}