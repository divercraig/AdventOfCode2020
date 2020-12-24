package day24

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class HexGridTest {

    @Test
    internal fun testConstructor() {
        val grid = HexGrid(fileName = "src/test/resources/day24/test_input1.txt")
        assertEquals(10, grid.blackCells.size, "There should be 10 black cells")
    }
}