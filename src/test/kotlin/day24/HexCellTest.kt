package day24

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class HexCellTest {

    @Test
    internal fun testEquals() {
        val cell = HexCell(1,2)
        var cell2 = HexCell(1,2)
        assertEquals(cell, cell2, "Cells with equal points should be equal")
    }

    @Test
    internal fun testFromString() {
        var cell = HexCell(0,0)
        var cellToTest = HexCell("nwwswee")
        assertEquals(cell, cellToTest, "Directions provided should create a cell with co-ords 0,0")
    }

    @Test
    internal fun testHashCode() {
        val cell = HexCell(1,2)
        var cell2 = HexCell(1,2)
        assertEquals(cell.hashCode(), cell2.hashCode(), "Cells with equal points should have the same hash code")
    }

    @Test
    internal fun testAdjacentCells() {
        val cell = HexCell(0,0)
        val adjacentCells = cell.adjacentCells()
        assertEquals(6, adjacentCells.size, "There should be 6 adjacent cells")
        assertTrue(adjacentCells.contains(HexCell(2,0)))
        assertTrue(adjacentCells.contains(HexCell(-2,0)))
        assertTrue(adjacentCells.contains(HexCell(1,1)))
        assertTrue(adjacentCells.contains(HexCell(1,-1)))
        assertTrue(adjacentCells.contains(HexCell(-1,1)))
        assertTrue(adjacentCells.contains(HexCell(-1,-1)))

    }
}