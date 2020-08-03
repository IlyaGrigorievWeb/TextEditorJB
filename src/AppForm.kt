import TextEditorJB.Components.TextPanel
import javax.swing.JFrame
import javax.swing.JPanel

class MyForm {

    companion object{

        var frame : JFrame = getFrame() as JFrame

        var panel : JPanel = getPanel() as JPanel



        private fun getFrame() : Any{
            val frame = JFrame()
            frame.isVisible = true
            frame.setBounds(750,250,500,500)
            frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            return frame
        }
        private fun getPanel() : Any{
            var panel = TextPanel()
            panel.isVisible = true;
            //panel.setBounds(0,0,300,300)
            return panel
        }
    }
}