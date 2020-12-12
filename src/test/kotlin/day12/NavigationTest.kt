package day12

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class NavigationTest {

    @Test
    internal fun testRealDataOnlyTurns90Degree() {
        val nav = Navigation(fileName = "src/main/resources/day12/input.txt")
        assertTrue(nav.areAllTurnsMultipleOf90(), "Expected all turns to be a value of 90")
    }

    @Test
    internal fun testDistanceAfterNav() {
        val nav = Navigation(fileName = "src/test/resources/day12/test_input1.txt")
        assertEquals(25, nav.distanceAfterNav(), "Expected distance after nav is not correct")
    }

    @Test
    internal fun testDistanceAfterWaypointing() {
        val nav = Navigation(fileName = "src/test/resources/day12/test_input1.txt")
        assertEquals(286, nav.distanceAfterWaypointing(), "Expected distance after waypointing is not correct")
    }
}