package TextEditorJB.Entities

class SourceText {

    private var editEvent : (() -> Unit)? = null

    var text = mutableListOf("")
    set(value){
        field = value
        editEvent!!.invoke()
    }

    var activeRow = 0
    set(value){
        if (value == -1)
        {
            field = 0
            if (positionInRow != 0)
                positionInRow = 0
        }
        else if (value == text.size)
        {
            field = text.lastIndex
            if (positionInRow != text[activeRow].lastIndex)
                positionInRow = text[activeRow].lastIndex + 1
        }
        else
        {
            field = value
            if (positionInRow > text[activeRow].lastIndex)
            {
                positionInRow = text[activeRow].lastIndex+1
            }
        }
    }

    var positionInRow = 0
        set(value){
            field = when (value) {
                -1 -> {
                    if (activeRow != 0)
                    {
                        activeRow --
                        text[activeRow].lastIndex + 1
                    }
                    else
                        field
                }
                text[activeRow].length + 1 -> {
                    if (activeRow != text.lastIndex)
                    {
                        activeRow ++
                        0
                    }
                    else
                        field
                }
                else -> value
            }
        }

    fun register( listener : () -> Unit)
    {
        editEvent = listener
    }
}