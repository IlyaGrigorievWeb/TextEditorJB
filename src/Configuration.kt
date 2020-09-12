import TextEditorJB.Components.TextPanel
import TextEditorJB.Entities.SourceText
import TextEditorJB.Listeners.KeyboardListener
import TextEditorJB.Listeners.MouseListener
import TextEditorJB.Services.*
import java.awt.Cursor
import java.awt.event.KeyEvent
import javax.swing.*

fun main(){

    val frame = getFrame() //TODO Повесить событие изменения размера окна


    val sourceText = SourceText()

    val panel =  TextPanel(sourceText)
    panel.isVisible = true
    panel.cursor = Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR)
            //frame.addMouseListener(CustomMouseListener())
            //panel.setBounds(0,0,300,300)

    val textSelectionService = TextSelectionService(panel,panel.navigationService,sourceText)
    //val fileService = FileService(panel,panel.workspaceService,sourceText)
    val textService = TextService(panel,sourceText, panel.navigationService)
    val shortcutService = ShortcutService(panel,textSelectionService,sourceText)

    frame.jMenuBar  = getConfiguredMenu(panel.fileService)
    frame.add(panel)
    panel.add(panel.caret)
    panel.add(panel.textSelection)

    frame.addKeyListener(KeyboardListener(panel, panel.navigationService, textSelectionService,textService,shortcutService, panel.workspaceService))

    val mouseService = MouseService(textSelectionService,panel.workspaceService,panel,panel.fileService)
    val ml = MouseListener(mouseService,panel)

    panel.addMouseListener(ml)
    panel.addMouseMotionListener(ml)
    panel.addMouseWheelListener(ml)

    frame.repaint()
    panel.revalidate()

}
fun getConfiguredMenu(fileService: FileService) : JMenuBar
{
    val jMenuBar  = JMenuBar()

    val fileOption = JMenu("File")
    jMenuBar.add(fileOption)

    val open = JMenuItem("Open",0)
    val save = JMenuItem("Save",0)

    fileOption.add(open)
    fileOption.add(save)

    open.addActionListener { fileService.open() }

    save.addActionListener { fileService.save() }
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