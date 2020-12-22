package day22

import java.io.File
import java.util.*
import kotlin.collections.ArrayDeque

@ExperimentalStdlibApi
class Combat(fileName:String) {
    var player1: ArrayDeque<Int>
    var player2: ArrayDeque<Int>

    init {
        val reader = File(fileName).bufferedReader()
        val queue1 = ArrayDeque<Int>()
        val queue2 = ArrayDeque<Int>()
        var target = ArrayDeque<Int>()
        while(reader.ready()) {
            val line = reader.readLine()
            when(line) {
                "Player 1:" -> target = queue1
                "Player 2:" -> target = queue2
                "" -> Unit
                else -> target.add(line.toInt())
            }
        }
        player1 = queue1
        player2 = queue2
    }

    fun play() {
        while(player1.size > 0 && player2.size > 0) {
            val draw1 = player1.removeFirst()
            val draw2 = player2.removeFirst()

            if(draw1 > draw2) {
                player1.addLast(draw1)
                player1.addLast(draw2)
            } else {
                player2.addLast(draw2)
                player2.addLast(draw1)
            }
        }
    }

    fun score() : Long {
        var score = 0L
        var winner = player1
        if(player1.size == 0) {
            winner = player2
        }
        winner.asReversed().forEachIndexed { index, card ->
            score += (index +1 ) * card
        }
        return score
    }
}