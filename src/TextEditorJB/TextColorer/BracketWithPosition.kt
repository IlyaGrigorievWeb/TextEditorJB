package TextEditorJB.TextColorer

class BracketWithPosition(row : Int,position : Int, bracket : Char) {
    var row = row
    var position = position
    var bracket = bracket
    var pairs = mapOf(Pair('{','}'),Pair('(',')'))
}