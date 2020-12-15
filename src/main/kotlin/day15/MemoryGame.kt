package day15

class MemoryGame(startingNumbers: List<Int>){
    val numberTurns = mutableMapOf<Int, MutableList<Int>>()
    var turn = 0
    var lastNumber = -1

    init {
        for(number in startingNumbers) {
            turn++
            addNumber(number)
        }
    }

    private fun addNumber(number: Int) {
        lastNumber = number
        if(number in numberTurns.keys) {
            numberTurns[number]!!.add(turn)
        } else {
            numberTurns[number] = mutableListOf(turn)
        }
    }

    private fun deriveNumber(): Int {
        val appearances = numberTurns[lastNumber]!!
        return if(appearances.size < 2) {
            0
        } else {
            appearances[appearances.size-1] - appearances[appearances.size-2]
        }
    }

    fun solveFor(lastTurn: Int): Int {
        while(turn < lastTurn) {
            turn++
            addNumber(deriveNumber())
        }
        return lastNumber
    }
}