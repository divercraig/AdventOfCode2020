package day10

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class JoltAdaptersTest {

    @Test
    internal fun testJoltDifferenceCalcSmallList() {
        val adapters = JoltAdapters(fileName = "src/test/resources/day10/test_input1.txt")
        assertEquals(35, adapters.joltDifferenceCalc(), "Jolt Difference Calc is not as expected")
    }

    @Test
    internal fun testJoltDifferenceCalcLongList() {
        val adapters = JoltAdapters(fileName = "src/test/resources/day10/test_input2.txt")
        assertEquals(220, adapters.joltDifferenceCalc(), "Jolt Difference Calc is not as expected")
    }
}