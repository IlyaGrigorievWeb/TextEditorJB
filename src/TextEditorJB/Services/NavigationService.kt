package TextEditorJB.Services

import TextEditorJB.Components.TextPanel

//Сервис работы с навигацией по тексту
class NavigationService(textPanel: TextPanel) {

    val panel = textPanel

    fun Up ()
    {
        if (panel.activeRow > 0){
            panel.activeRow--
            panel.caret.moveUp()
        }

        panel.paint(panel.graphics)
    }
    fun Down ()
    {
        if (panel.activeRow < panel.fullText.lastIndex){
            panel.activeRow++
            panel.caret.moveDown()
        }

        panel.paint(panel.graphics)
    }
    fun Left ()
    {
        panel.caret.moveLeft()
        panel.paint(panel.graphics)
    }
    fun Right ()
    {
        panel.caret.moveRight()
        panel.paint(panel.graphics)
    }
    fun Home ()
    {
        panel.caret.moveHome()

        panel.paint(panel.graphics)
    }
    fun End ()
    {
        panel.caret.moveEnd()

        panel.paint(panel.graphics)
    }

    fun PageUp ()
    {
        var height = panel.height
        var lines = height / panel.lineSpacing
        for (i in 1..lines)
            Up()

        panel.paint(panel.graphics)
    }

    fun PageDown ()
    {
        var height = panel.height
        var lines = height / panel.lineSpacing
        for (i in 1..lines)
            Down()

        panel.paint(panel.graphics)
    }
}