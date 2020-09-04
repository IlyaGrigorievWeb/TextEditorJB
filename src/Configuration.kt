import TextEditorJB.Components.TextPanel
import TextEditorJB.Listeners.KeyboardListener
import TextEditorJB.Listeners.MouseListener
import TextEditorJB.Services.*
import java.awt.event.KeyEvent
import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem
import javax.swing.KeyStroke

fun main(args: Array<String>){
    val panel = MyForm.panel as TextPanel
    val navigationService = NavigationService(panel)
    val textSelectionService = TextSelectionService(panel,navigationService)
    val workspaceService = WorkspaceService(panel)
    val fileService = FileService(panel,workspaceService)
    val textService = TextService(panel,workspaceService)

    MyForm.frame.jMenuBar  = getConfiguredMenu(fileService)
    MyForm.frame.add(MyForm.panel)
    panel.add((MyForm.panel as TextPanel).caret)
    panel.add((MyForm.panel as TextPanel).textSelection)

    MyForm.frame.addKeyListener(KeyboardListener(panel, navigationService, textSelectionService, fileService,textService))

    val mouseService = MouseService(textSelectionService,workspaceService,panel)
    var ml = MouseListener(mouseService)

    panel.addMouseListener(ml)
    panel.addMouseMotionListener(ml)
    panel.addMouseWheelListener(ml)

    MyForm.frame.repaint()
    MyForm.panel.revalidate()

}
fun getConfiguredMenu(fileService: FileService) : JMenuBar
{
    var jMenuBar  = JMenuBar()

    val fileOption = JMenu("File")
    jMenuBar.add(fileOption)

    val open = JMenuItem("Open",0)
    val save = JMenuItem("Save",0)

    fileOption.add(open)
    fileOption.add(save)

    open.addActionListener { fileService.open() }
//    save.addActionListener {
//        println("saved")
//    }
    save.addActionListener { fileService.save() }
    save.accelerator = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK)

    return jMenuBar
}