package day9

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class XmasCypherAttackTest {

    @Test
    internal fun testFindVulnerabilityTarget() {
        val attack = XmasCypherAttack(fileName = "src/test/resources/day9/test_input1.txt")
        assertEquals(127, attack.findVulnerabilityTarget(5), "First vulnerability target should be 127")
    }

    @Test
    internal fun testFindEncryptionWeakness() {
        val attack = XmasCypherAttack(fileName = "src/test/resources/day9/test_input1.txt")
        assertEquals(62, attack.findWeakness(5), "Weakness should be 62")
    }
}