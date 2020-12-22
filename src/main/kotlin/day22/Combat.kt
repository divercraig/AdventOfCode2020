package day22

import java.io.File
import kotlin.collections.ArrayDeque

@ExperimentalStdlibApi
class Combat {
    var deck1: ArrayDeque<Int>
    var deck2: ArrayDeque<Int>
    var player1Wins = false
    var player2Wins = false
    var defaultWin = false
    var previousStates = mutableSetOf<MutableList<Int>>()

    constructor(deck1: Collection<Int>, deck2: Collection<Int>) {
        this.deck1 = ArrayDeque(deck1)
        this.deck2 = ArrayDeque(deck2)
    }

    constructor(fileName:String) {
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
        deck1 = queue1
        deck2 = queue2
    }

    fun play() {
        while(deck1.size > 0 && deck2.size > 0) {
            val draw1 = deck1.removeFirst()
            val draw2 = deck2.removeFirst()

            if(draw1 > draw2) {
                deck1.addLast(draw1)
                deck1.addLast(draw2)
            } else {
                deck2.addLast(draw2)
                deck2.addLast(draw1)
            }
        }
        if(deck1.size > 0) {
            player1Wins = true
        } else {
            player2Wins = true
        }
    }

    fun playRecursive() {
        var round = 0
        while(!player1Wins && !player2Wins) {
            round++
            val state = deck1.toMutableList()

            // Add -1 as it's a delimiter between the two decks
            // This prevents us incorrectly defaulting a game to player 1 which can occur when
            // the cards are in the same order but players are holding a different number
            state.add(-1)

            state.addAll(deck2.toList())
            if (previousStates.contains(state)) {
                player1Wins = true
                defaultWin = true
                return
            }
            previousStates.add(state)
            val card1 = deck1.removeFirst()
            val card2 = deck2.removeFirst()

            if(deck1.size >= card1 && deck2.size >= card2) {
                val subGame = Combat(deck1.subList(0, card1), deck2.subList(0, card2))
                subGame.playRecursive()
                if(subGame.player1Wins) {
                    deck1.addLast(card1)
                    deck1.addLast(card2)
                } else {
                    deck2.addLast(card2)
                    deck2.addLast(card1)
                }
            } else {
                if(card1 > card2) {
                    deck1.addLast(card1)
                    deck1.addLast(card2)
                } else {
                    deck2.addLast(card2)
                    deck2.addLast(card1)
                }
            }

            if(deck1.size == 0) {
                player2Wins = true
            }

            if(deck2.size == 0) {
                player1Wins = true
            }
        }
    }

    fun score() : Long {
        var score = 0L
        var winner = deck1
        if(player2Wins) {
            winner = deck2
        }
        winner.asReversed().forEachIndexed { index, card ->
            score += (index +1 ) * card
        }
        return score
    }
}