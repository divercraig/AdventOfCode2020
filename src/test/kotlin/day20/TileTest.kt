package day20

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import util.Point2D
import java.io.File

class TileTest {

    @Test
    internal fun testTileID() {
        val input = File("src/test/resources/day20/test_single_title_input.txt").useLines { it.toList() }
        val tile = Tile(input)
        assertEquals(2311, tile.id, "Tile ID is not as expected")
    }

    @Test
    internal fun testTileCornerPoints() {
        val input = File("src/test/resources/day20/test_single_title_input.txt").useLines { it.toList() }
        val tile = Tile(input)
        assertEquals('.', tile.pixels[Point2D(0,0)]!!, "Top Left pixel is not as expected")
        assertEquals('.', tile.pixels[Point2D(9,0)]!!, "Top Right pixel is not as expected")
        assertEquals('.', tile.pixels[Point2D(0,9)]!!, "Bottom Left pixel is not as expected")
        assertEquals('#', tile.pixels[Point2D(9,9)]!!, "Bottom Right pixel is not as expected")
    }

    @Test
    internal fun testTileEdgeLength() {
        val input = File("src/test/resources/day20/test_single_title_input.txt").useLines { it.toList() }
        val tile = Tile(input)
        assertEquals(10, tile.edgeLength, "Edge Length is not as expected")
    }

    @Test
    internal fun testGetEdges() {
        val input = File("src/test/resources/day20/test_single_title_input.txt").useLines { it.toList() }
        val tile = Tile(input)
        val expectedTop = "..##.#..#."
        val expectedBottom = "..###..###"
        val expectedRight = "...#.##..#"
        val expectedLeft = ".#####..#."

        assertEquals(expectedTop, tile.edges[0], "Top edge not as expected")
        assertEquals(expectedRight, tile.edges[1], "Right edge not as expected")
        assertEquals(expectedBottom, tile.edges[2], "Bottom edge not as expected")
        assertEquals(expectedLeft, tile.edges[3], "Left edge not as expected")

    }
}