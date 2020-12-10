package day10

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigInteger

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

    @Test
    internal fun testNumberOfValidArrangementsSmallList() {
        val adapters = JoltAdapters(fileName = "src/test/resources/day10/test_input1.txt")
        assertEquals(8L, adapters.numberOfValidArrangements(), "Number of Valid Arrangements is not as expected")
    }

    @Test
    internal fun testNumberOfValidArrangementsLongList() {
        val adapters = JoltAdapters(fileName = "src/test/resources/day10/test_input2.txt")
        assertEquals(19208L, adapters.numberOfValidArrangements(), "Number of Valid Arrangements is not as expected")
    }
}