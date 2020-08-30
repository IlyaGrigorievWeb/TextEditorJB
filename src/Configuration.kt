import TextEditorJB.Actions.*
import TextEditorJB.Components.TextPanel
import TextEditorJB.Services.MouseService
import TextEditorJB.Services.NavigationService
import TextEditorJB.Services.TextSelectionService

fun main(args: Array<String>){
    val panel = MyForm.panel as TextPanel

    MyForm.frame.add(MyForm.panel)
    panel.add((MyForm.panel as TextPanel).caret)
    panel.add((MyForm.panel as TextPanel).textSelection)

    val navigationService = NavigationService(panel)
    val textSelectionService = TextSelectionService(panel,navigationService)
    MyForm.frame.addKeyListener(KeyboardListener(panel,navigationService,textSelectionService))

    val mouseService = MouseService(textSelectionService)
    var ml = MouseListener(mouseService)

    panel.addMouseListener(ml)
    panel.addMouseMotionListener(ml)
    panel.addMouseWheelListener(ml)

    MyForm.frame.repaint()
    MyForm.panel.revalidate()

}