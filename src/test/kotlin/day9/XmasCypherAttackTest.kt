package day9

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class XmasCypherAttackTest {

    @Test
    internal fun testFindFirstWeakness() {
        val attack = XmasCypherAttack(fileName = "src/test/resources/day9/test_input1.txt")
        assertEquals(127, attack.findFirstWeakness(5), "First weakness should be 127")
    }
}