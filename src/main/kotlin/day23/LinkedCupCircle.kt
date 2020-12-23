package day23

import java.util.*


class LinkedCupCircle(input: MutableList<Int>) {
    val cups = TreeSet<Cup>()
    val originalCup: Cup
    var currentCup: Cup

    init {
        input.addAll((input.max()!! +1)..1000000)
        originalCup = linkCup(input)
        currentCup = originalCup
    }

    private fun linkCup(labels: List<Int>): Cup {
        val first = Cup(labels[0])
        var previous = first
        cups.add(first)
        for(i in 1 until labels.size) {
            val next = Cup(labels[i])
            cups.add(next)
            previous.next = next
            previous = next
        }
        previous.next = first
        return first
    }

    fun playOneRound() {
        val selected = listOf(currentCup.next, currentCup.next.next, currentCup.next.next.next)
        val newNext = currentCup.next.next.next.next

        currentCup.next = newNext
        cups.removeAll(selected)

        val destCup = cups.lower(currentCup) ?: cups.last()
        val after = destCup.next
        destCup.next = selected[0]
        selected[2].next = after

        cups.addAll(selected)
        currentCup = newNext
    }

    fun playRounds(num: Int) {
        repeat(num) {
            playOneRound()
        }
    }

    fun finalState(): Long {
        val cupOne = cups.floor(Cup(1))
        val first = cupOne.next
        val second = first.next

        return first.label.toLong() * second.label.toLong()
    }
}