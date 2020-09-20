import TextEditorJB.Components.TextPanel
import TextEditorJB.Entities.SourceText
import TextEditorJB.Listeners.KeyboardListener
import TextEditorJB.Listeners.MouseListener
import TextEditorJB.Services.*
import java.awt.Cursor
import java.awt.Dimension
import java.awt.event.KeyEvent
import javax.swing.*

fun main(){

    val frame = getFrame() //TODO Повесить событие изменения размера окна

    val sourceText = SourceText()

    val panel =  TextPanel(sourceText)
    panel.isVisible = true
    panel.cursor = Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR)

    val textService = TextService(panel, panel.navigationService)
    val shortcutService = ShortcutService(panel.textSelectionService)

    frame.jMenuBar  = getConfiguredMenu(panel.fileService, sourceText)
    frame.add(panel)
    panel.add(panel.caret)
    panel.add(panel.textSelection)

    frame.addKeyListener(KeyboardListener(panel, panel.navigationService, panel.textSelectionService,textService,shortcutService, panel.workspaceService,sourceText))

    val mouseService = MouseService(panel.textSelectionService,panel.workspaceService,panel,sourceText)
    val ml = MouseListener(mouseService,panel)

    panel.addMouseListener(ml)
    panel.addMouseMotionListener(ml)
    panel.addMouseWheelListener(ml)

    frame.repaint()
    panel.revalidate()

}
fun getConfiguredMenu(fileService: FileService, sourceText : SourceText) : JMenuBar
{
    val jMenuBar  = JMenuBar()

    val fileOption = JMenu("File")
    jMenuBar.add(fileOption)

    val open = JMenuItem("Open",0)
    val save = JMenuItem("Save",0)

    fileOption.add(open)
    fileOption.add(save)

    open.addActionListener { fileService.open(sourceText) }

    save.addActionListener { fileService.save(sourceText) }
    save.accelerator = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK)

    return jMenuBar
}
private fun getFrame() : JFrame{
    val frame = JFrame()
    frame.isVisible = true
    frame.setBounds(750,250,500,500)
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.focusTraversalKeysEnabled = false
    return frame
}
