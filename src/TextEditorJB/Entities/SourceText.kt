package TextEditorJB.Entities

class SourceText {

    private var editEvent : (() -> Unit)? = null
    var activeRow = 0
    var text = mutableListOf("")
    set(value){
        field = value
        editEvent!!.invoke()
    }
    var positionInRow = 0

    fun register( listener : () -> Unit)
    {
        editEvent = listener
    }
}