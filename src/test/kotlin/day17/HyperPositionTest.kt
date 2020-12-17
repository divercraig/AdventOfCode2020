package day17

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class HyperPositionTest {

    @Test
    internal fun testEquals() {
        val first = HyperPosition(1,2,3,4)
        val second = HyperPosition(1,2,3,4)
        val notMatch = HyperPosition(4,3,2,1)
        assertEquals(first, second, "Hyperpositions with the same values should be equal")
        assertNotEquals(first, notMatch, "Hyperpositions with different values should not be equal")
    }
}