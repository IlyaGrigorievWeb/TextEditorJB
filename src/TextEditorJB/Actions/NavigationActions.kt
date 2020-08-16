package TextEditorJB.Actions

import TextEditorJB.Components.TextPanel

class NavigationActions(textPanel: TextPanel)
{
    var textPanel = textPanel

    fun Up ()
    {
        var panel = MyForm.panel as TextPanel

        if (panel.activeRow > 0){
            panel.activeRow--

            panel.caret.moveUp()
        }

        panel.paint(panel.graphics)
    }
    fun Down ()
    {
        var panel = MyForm.panel as TextPanel

        if (panel.activeRow < panel.fullText.lastIndex){
            panel.activeRow++

            panel.caret.moveDown()
        }

        panel.paint(panel.graphics)
    }
    fun Left ()
    {

    }
    fun Right ()
    {

    }
    fun Home ()
    {
        var panel = MyForm.panel as TextPanel

        panel.caret.moveHome()

        panel.paint(panel.graphics)
    }
    fun End ()
    {
        var panel = MyForm.panel as TextPanel

        panel.caret.moveEnd()

        panel.paint(panel.graphics)
    }

    fun PageUp ()
    {
        var panel = MyForm.panel as TextPanel

        var height = panel.height
        var lines = height / panel.lineSpacing
        for (i in 1..lines)
            Up()

        panel.paint(panel.graphics)
    }

    fun PageDown ()
    {
        var panel = MyForm.panel as TextPanel

        var height = panel.height
        var lines = height / panel.lineSpacing
        for (i in 1..lines)
            Down()

        panel.paint(panel.graphics)
    }

}