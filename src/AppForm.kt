import TextEditorJB.Components.TextPanel
import java.awt.Cursor
import java.awt.Panel
import java.io.File
import javax.swing.JFrame
import javax.swing.JPanel

class AppForm {

    companion object{

        var openingFile : File? = null
        var frame : JFrame = getFrame() as JFrame //TODO Повесить событие изменения размера окна

        private fun getFrame() : Any{
            val frame = JFrame()
            frame.isVisible = true
            frame.setBounds(750,250,500,500)
            frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE

            frame.focusTraversalKeysEnabled = false
            return frame
        }
    }
}
