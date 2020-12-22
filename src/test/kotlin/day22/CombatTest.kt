package day22

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@ExperimentalStdlibApi
class CombatTest {

    @Test
    internal fun testConstruction() {
        val combat = Combat(fileName = "src/test/resources/day22/test_input1.txt")
        assertEquals(5, combat.player1.size, "Player 1 should have 5 cards")
        assertEquals(5, combat.player2.size, "Player 1 should have 5 cards")
        assertEquals(9, combat.player1.removeFirst(), "Player 1 should have 9 first")
        assertEquals(5, combat.player2.removeFirst(), "Player 2 should have 5 first")
        assertEquals(4, combat.player1.size, "Player 1 should now have 4 cards")
        assertEquals(4, combat.player2.size, "Player 2 should now have 4 cards")

    }

    @Test
    internal fun testPlay() {
        val combat = Combat(fileName = "src/test/resources/day22/test_input1.txt")
        combat.play()
        assertEquals(0, combat.player1.size, "Player 1 should have lost")
        assertEquals(10, combat.player2.size, "Player 2 should have won")
        assertEquals(listOf(3,2,10,6,8,5,9,4,7,1), combat.player2.toList(), "Player 2's deck is not as expected after winning")
    }

    @Test
    internal fun testScore() {
        val combat = Combat(fileName = "src/test/resources/day22/test_input1.txt")
        combat.play()
        assertEquals(306L, combat.score(), "Final score is not as expected")
    }
}