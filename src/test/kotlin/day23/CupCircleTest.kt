package day23

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CupCircleTest {

    @Test
    internal fun test10Rounds() {
        val input = listOf(3,8,9,1,2,5,4,6,7)
        val circle = CupCircle(input)
        circle.playRounds(10)
        assertEquals("92658374", circle.finalState(), "Final State after 10 rounds is not as expected")
    }

    @Test
    internal fun test100Rounds() {
        val input = listOf(3,8,9,1,2,5,4,6,7)
        val circle = CupCircle(input)
        circle.playRounds(100)
        assertEquals("67384529", circle.finalState(), "Final State after 10 rounds is not as expected")
    }
}