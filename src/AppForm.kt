import TextEditorJB.Actions.KeyboardListener
import TextEditorJB.Components.TextPanel
import TextEditorJB.FileService.getConfiguredMenu
import java.awt.Cursor
import java.io.File
import javax.swing.JFrame
import javax.swing.JPanel

class MyForm {

    companion object{

        var openingFile : File? = null
        var panel : JPanel = getPanel() as JPanel
        var frame : JFrame = getFrame() as JFrame



        private fun getFrame() : Any{
            val frame = JFrame()
            frame.isVisible = true
            frame.setBounds(750,250,500,500)
            frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            frame.jMenuBar = getConfiguredMenu()
            frame.addKeyListener(KeyboardListener(panel as TextPanel))
            frame.focusTraversalKeysEnabled = false
            return frame
        }
        private fun getPanel() : Any{
            var panel = TextPanel()
            panel.isVisible = true;
            panel.cursor = Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR)
            //frame.addMouseListener(CustomMouseListener())
            //panel.setBounds(0,0,300,300)
            return panel
        }
    }
}
