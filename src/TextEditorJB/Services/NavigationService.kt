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
    }
    fun Down ()
    {
        if (panel.activeRow < panel.fullText.lastIndex){
            panel.activeRow++
            panel.caret.moveDown()
        }
    }
    fun Left ()
    {
        panel.caret.moveLeft()
    }
    fun Right ()
    {
        panel.caret.moveRight()
    }
    fun Home ()
    {
        panel.caret.moveHome()
    }
    fun End ()
    {
        panel.caret.moveEnd()
    }

    fun PageUp ()
    {
        var height = panel.height
        var lines = height / panel.lineSpacing
        for (i in 1..lines)
            Up()
    }

    fun PageDown ()
    {
        var height = panel.height
        var lines = height / panel.lineSpacing
        for (i in 1..lines)
            Down()
    }
}