package day24

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class HexGridTest {

    @Test
    internal fun testConstructor() {
        val grid = HexGrid(fileName = "src/test/resources/day24/test_input1.txt")
        assertEquals(10, grid.blackCells(), "There should be 10 black cells")
    }

    @Test
    internal fun testOneDay() {
        val grid = HexGrid(fileName = "src/test/resources/day24/test_input1.txt")
        grid.processOneDay()
        assertEquals(15, grid.blackCells(), "There should be 15 black cells after 1 day")
    }

    @Test
    internal fun testTwoDays() {
        val grid = HexGrid(fileName = "src/test/resources/day24/test_input1.txt")
        grid.processXDays(2)
        assertEquals(12, grid.blackCells(), "There should be 12 black cells after 2 day")
    }

    @Test
    internal fun test100Days() {
        val grid = HexGrid(fileName = "src/test/resources/day24/test_input1.txt")
        grid.processXDays(100)
        assertEquals(2208, grid.blackCells(), "There should be 2208 black cells after 100 days")
    }
}