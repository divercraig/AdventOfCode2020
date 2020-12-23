package day23

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LinkedCupCircleTest {

    @Test
    internal fun test10MillionRounds() {
        val input = mutableListOf(3,8,9,1,2,5,4,6,7)
        val circle = LinkedCupCircle(input)
        circle.playRounds(10000000)
        assertEquals(149245887792L, circle.finalState(), "product of next two")
    }
}