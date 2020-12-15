package day15

class MemoryGame(startingNumbers: List<Int>){
    val numberTurns = mutableMapOf<Int, IntArray>()
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
            numberTurns[number]!![0] = numberTurns[number]!![1]
            numberTurns[number]!![1] = turn

        } else {
            numberTurns[number] = IntArray(2) { -1 }
            numberTurns[number]?.set(1, turn)
        }
    }

    private fun deriveNumber(): Int {
        val appearances = numberTurns[lastNumber]!!
        return if(appearances[0] == -1) {
            0
        } else {
            appearances[1] - appearances[0]
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