package TextEditorJB.TextColorer

import TextEditorJB.Components.TextPanel
import java.awt.Color
import java.awt.FontMetrics
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.geom.Rectangle2D

class BracketsService { //TODO Баг }  {
    var openBrackets = mutableListOf<BracketWithPosition?>()
    var closeBrackets = mutableListOf<BracketWithPosition?>()
    // Описание работы хранилища скобок: есть пары скобок (открыв./закрыв.) , добавляем скобку, если другая скобка ожидает пару то даем ей пару, если нет ищем скобку выше которая ожидает пару, если таких нет ставим без пары
    // { } { { } в таком кейсе сейчашний алгоритм косячит
    fun addBracket(row : Int, position: Int, bracket: Char){
    if (bracket == '{')
    {
        var isPasted = false
        for ((index,openBracket) in openBrackets.withIndex()) {
            if (openBrackets[index] == null)
            {
                openBrackets[index] = BracketWithPosition(row, position, bracket)
                isPasted = true
                break
            }
        }
        if (!isPasted) {
            openBrackets.add(0, BracketWithPosition(row, position, bracket))
            closeBrackets.add(0,null)
        }
    }
    else if (bracket == '}'){
        var isPasted = false
        for ((index,closeBracket) in closeBrackets.withIndex()) {
            if (closeBrackets[index] == null)
            {
                closeBrackets[index] = BracketWithPosition(row, position, bracket)
                isPasted = true
                break
            }
        }
        if (!isPasted) {
            closeBrackets.add(0, BracketWithPosition(row, position, bracket))
            openBrackets.add(0,null)
        }
    }
}

fun getPairBracket (row : Int,position : Int,inputBracket : Char) : BracketWithPosition?{
    var inputBracketIndex = -1
    if (inputBracket == '{') {
        for ((index, bracket) in openBrackets.withIndex()) {
            if (bracket != null && bracket.row == row && bracket.position == position) {
                inputBracketIndex = index
            }
        }
        if (inputBracketIndex == -1)
            return null
        else {
            return closeBrackets[inputBracketIndex]
        }
    }
    else if (inputBracket == '}')
        for ((index,bracket) in closeBrackets.withIndex()){
            if (bracket != null && bracket.row == row && bracket.position == position){
                inputBracketIndex = index
            }
        }
    if(inputBracketIndex == -1)
        return null
    else{
        return openBrackets[inputBracketIndex]
    }
}
}