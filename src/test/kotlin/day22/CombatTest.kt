package day22

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

@ExperimentalStdlibApi
class CombatTest {

    @Test
    internal fun testConstruction() {
        val combat = Combat(fileName = "src/test/resources/day22/test_input1.txt")
        assertEquals(5, combat.deck1.size, "Player 1 should have 5 cards")
        assertEquals(5, combat.deck2.size, "Player 1 should have 5 cards")
        assertEquals(9, combat.deck1.removeFirst(), "Player 1 should have 9 first")
        assertEquals(5, combat.deck2.removeFirst(), "Player 2 should have 5 first")
        assertEquals(4, combat.deck1.size, "Player 1 should now have 4 cards")
        assertEquals(4, combat.deck2.size, "Player 2 should now have 4 cards")

    }

    @Test
    internal fun testSecondConstructor() {
        val combat = Combat(fileName = "src/test/resources/day22/test_input1.txt")
        val combat2 = Combat(deck1 = combat.deck1, deck2 = combat.deck2)
        assertEquals(5, combat2.deck1.size, "Game 2 Player 1 should have 5 cards")
        assertEquals(5, combat2.deck2.size, "Game 2 Player 1 should have 5 cards")
        assertNotSame(combat.deck1, combat2.deck1, "Games should copy decks")
        assertNotSame(combat.deck2, combat2.deck2, "Games should copy decks")
        assertEquals(9, combat2.deck1.removeFirst(), "Game 2 Player 1 should have 9 first")
        assertEquals(5, combat2.deck2.removeFirst(), "Game 2 Player 2 should have 5 first")
    }

    @Test
    internal fun testPlay() {
        val combat = Combat(fileName = "src/test/resources/day22/test_input1.txt")
        combat.play()
        assertFalse(combat.player1Wins, "Player 1 should have lost")
        assertTrue(combat.player2Wins, "Player 2 should have won")
        assertEquals(listOf(3,2,10,6,8,5,9,4,7,1), combat.deck2.toList(), "Player 2's deck is not as expected after winning")
    }

    @Test
    internal fun testScore() {
        val combat = Combat(fileName = "src/test/resources/day22/test_input1.txt")
        combat.play()
        assertEquals(306L, combat.score(), "Final score is not as expected")
    }

    @Test
    internal fun testPlayRecursive() {
        val combat = Combat(fileName = "src/test/resources/day22/test_input1.txt")
        combat.playRecursive()
        assertFalse(combat.player1Wins, "Player 1 should have lost")
        assertTrue(combat.player2Wins, "Player 2 should have won")
        assertEquals(listOf(7,5,6,2,4,1,10,8,9,3), combat.deck2.toList(), "Player 2's deck is not as expected after winning")
    }

    @Test
    internal fun testPlayRecursiveScore() {
        val combat = Combat(fileName = "src/test/resources/day22/test_input1.txt")
        combat.playRecursive()
        assertEquals(291L, combat.score(), "Final score after recurrsion is not as expected")
    }

    @Test
    internal fun testDefaultWinScenario() {
        var combat = Combat(fileName = "src/test/resources/day22/test_input2.txt")
        combat.playRecursive()
        assertTrue(combat.player1Wins, "Player 1 should win")
        assertTrue(combat.defaultWin, "Win should be by default")
    }
}