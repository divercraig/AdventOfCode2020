package day20

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TileMatcherTest {

    @Test
    internal fun testTileMap() {
        val matcher = TileMatcher(fileName = "src/test/resources/day20/test_input1.txt")
        assertEquals(9, matcher.tileMap.size, "matcher doesn't have the expected number of tiles")

        var expectedIds = mutableSetOf(2311, 1951, 1171, 1427, 1489, 2473, 2971, 2729, 3079)
        for(id in matcher.tileMap.keys) {
            assertTrue(expectedIds.contains(id), "Couldn't find id $id in the list of expected ids")
            expectedIds.remove(id)
        }

        for(tile in matcher.tileMap.values) {
            assertEquals(100, tile.pixels.size, "Tile ${tile.id} doesn't have the expected number of pixels")
        }
    }

    @Test
    internal fun testEdgeMap() {
        val matcher = TileMatcher(fileName = "src/test/resources/day20/test_input1.txt")
        var sumEdges = 0
        for(edgeSet in matcher.edgeMap.values) {
            sumEdges += edgeSet.size
        }
        assertEquals(36, sumEdges, "Matcher didn't find the expected number of edges")
    }

    @Test
    internal fun testReversedEdgeMap() {
        val matcher = TileMatcher(fileName = "src/test/resources/day20/test_input1.txt")
        var sumEdges = 0
        for(edgeSet in matcher.reversedEdgeMap.values) {
            sumEdges += edgeSet.size
        }
        assertEquals(36, sumEdges, "Matcher didn't find the expected number of reversed edges")
    }

    @Test
    internal fun testProductOfCornerIds() {
        val matcher = TileMatcher(fileName = "src/test/resources/day20/test_input1.txt")
        assertEquals(20899048083289L, matcher.productOfCornerIds(), "Matcher corner product didn't meet expectations")
    }

    @Test
    internal fun testNumberOfTilesPerEdge() {
        val matcher = TileMatcher(fileName = "src/test/resources/day20/test_input1.txt")
        assertEquals(3, matcher.numberOfTilesPerEdge(), "Matcher not identifying the number of tiles per edge correctly")
    }

    @Test
    internal fun testSolvePart2() {
        val matcher = TileMatcher(fileName = "src/test/resources/day20/test_input1.txt")
        assertEquals(273, matcher.solvePart2(), "Part 2 isn't as expected")
    }
}