package day20

import util.Point2D
import util.printMapOfPoints
import java.awt.Point
import java.io.File

class TileMatcher(fileName: String) {
    val tileMap: Map<Int, Tile>
    val edgeMap = mutableMapOf<String, MutableSet<Pair<Int, Int>>>()
    val reversedEdgeMap = mutableMapOf<String, MutableSet<Pair<Int, Int>>>()
    var finalImage = mutableMapOf<Point2D, Char>()

    init {
        val mutableTileMap = mutableMapOf<Int, Tile>()

        val reader = File(fileName).bufferedReader()
        var currentTile = mutableListOf<String>()
        var line: String

        while(reader.ready()) {
            line = reader.readLine()
            if(line == "") {
                val tile = Tile(currentTile)
                mutableTileMap[tile.id]  = tile
                currentTile = mutableListOf()
            } else {
                currentTile.add(line)
            }
        }
        val tile = Tile(currentTile)
        mutableTileMap[tile.id] = tile
        tileMap = mutableTileMap

        generateEdgeMap()
    }

    private fun sumMatchingEdges(edge: String, excludingId: Int) : Int {
        var sum = 0
        val matchingEdges = edgeMap[edge]
        if(matchingEdges != null) {
            sum += matchingEdges.filter { it -> it.first != excludingId }.size
        }
        val matchingReversedEdges = reversedEdgeMap[edge]
        if(matchingReversedEdges!= null) {
            sum += matchingReversedEdges.filter { it -> it.first != excludingId}.size
        }
        return sum
    }

    private fun findTileWithMatchingEdge(edge: String, excludingID: Int): Int {
        val matchingEdges = edgeMap[edge]
        if(matchingEdges != null) {
            for(found in matchingEdges) {
                if(found.first != excludingID)
                    return found.first
            }
        }

        val matchingReverseEdges = reversedEdgeMap[edge]
        if(matchingReverseEdges != null) {
            for(found in matchingReverseEdges) {
                if(found.first != excludingID) {
                    return found.first
                }
            }
        }
        throw Exception("BUGGER CAN'T FIND A MATCH")
    }

    private fun generateEdgeMap() {
        for(tile in tileMap.values) {
            tile.edges.forEachIndexed { i, edge ->
                if(edgeMap.keys.contains(edge)) {
                    edgeMap[edge]!!.add(Pair(tile.id, i))
                } else {
                    edgeMap[edge] = mutableSetOf(Pair(tile.id, i))
                }

                val reversedEdge = edge.reversed()

                if(reversedEdgeMap.keys.contains(reversedEdge)) {
                    reversedEdgeMap[reversedEdge]!!.add(Pair(tile.id, i))
                } else {
                    reversedEdgeMap[reversedEdge] = mutableSetOf(Pair(tile.id, i))
                }
            }
        }
    }

    private fun findCorners() : Set<Int> {
        val edgeCount = mutableMapOf<Int, Int>()
        for(tile in tileMap.values) {
            var sum = 0
            for(edge in tile.edges) {
                sum += edgeMap[edge]?.size ?: 0
                sum += reversedEdgeMap[edge]?.size ?: 0
            }
            edgeCount[tile.id] = sum
        }

        val ids = mutableSetOf<Int>()
        repeat(4) {
            val minId = edgeCount.minBy { it.value }
            ids.add(minId!!.key)
            edgeCount.remove(minId.key)
        }
        return ids
    }

    fun numberOfTilesPerEdge() : Int {
        var i = 1
        while(i*i <= tileMap.size) {
            i++
        }
        return i-1
    }

    private fun createFullImage() {
        val availableTiles = tileMap.toMutableMap()
        var compositePixels = mutableMapOf<Point2D, Char>()
        val tileArrangement = mutableMapOf<Point2D, Tile>()
        val originTileId = findCorners().first()
        val originTile = availableTiles[originTileId]!!

        rotateTileUntilMatchingEdges(originTile, setOf(1,2))


        tileArrangement[Point2D(0,0)] = originTile
        availableTiles.remove(originTileId)


        var x = 0
        var y = 0

        //Top Row
        while(x < numberOfTilesPerEdge()-1) {
            x++
            val tileToMatch = tileArrangement[Point2D(x-1,y)]!!
            val edgeToMatch = tileToMatch.edges[1]
            val nextTile = tileMap[findTileWithMatchingEdge(edgeToMatch, tileToMatch.id)]!!
            nextTile.moveUntilEdgeMatches(3,edgeToMatch)
            tileArrangement[Point2D(x,y)] = nextTile

        }

        for(y in 1 until numberOfTilesPerEdge()) {
            for(x in 0 until numberOfTilesPerEdge()) {
                val tileToMatch = tileArrangement[Point2D(x,y-1)]!!
                val edgeToMatch = tileToMatch.edges[2]
                val nextTile = tileMap[findTileWithMatchingEdge(edgeToMatch, tileToMatch.id)]!!
                nextTile.moveUntilEdgeMatches(0,edgeToMatch)
                tileArrangement[Point2D(x,y)] = nextTile
            }
        }

        for(entry in tileArrangement) {
            val xOffset = entry.key.x * entry.value.edgeLength
            val yOffset = entry.key.y * entry.value.edgeLength
            val pixels = entry.value.pixels
            for(pixelEntry in pixels) {
                var pxX = pixelEntry.key.x
                var pxY = pixelEntry.key.y
                compositePixels[Point2D(xOffset+pxX, yOffset+pxY)] = pixelEntry.value

            }
        }

        val compositePixelsCopy = compositePixels.toMutableMap()
        for(point in compositePixels.keys) {
            val x = point.x
            val y = point.y

            if(x%10==0 || x%10==9 || y%10==0 || y%10==9) {
                compositePixelsCopy.remove(point)
            }
        }
        compositePixels = compositePixelsCopy

        compositePixels = repositionToRemoveEmpty(compositePixels)

        finalImage = identifySeaMonsters(compositePixels)

        println("")
        printMapOfPoints(finalImage)
        println("")


    }

    private fun repositionToRemoveEmpty(image:MutableMap<Point2D, Char>):MutableMap<Point2D, Char> {
        val imageCopy = mutableMapOf<Point2D, Char>()
        val pointMapping = mutableMapOf<Int, Int>()
        var maxX = 0
        for(point in image.keys) {
            if(point.x > maxX) {
                maxX = point.x
            }
        }
        var offset = 0
        for(x in 0..maxX) {
            if(x%10==0 || x%10==9){
                offset--
            } else {
                pointMapping[x] = x+offset
            }
        }
        for(entry in image) {
            var translated = Point2D(pointMapping[entry.key.x]!!, pointMapping[entry.key.y]!!)
            imageCopy[translated] = entry.value
        }
        return imageCopy
    }

    private fun identifySeaMonsters(image:MutableMap<Point2D, Char>): MutableMap<Point2D, Char>{
        val seaMonsterOffsets = setOf(
                Pair(18, 0),
                Pair(0,1), Pair(5,1), Pair(6,1), Pair(11,1), Pair(12,1), Pair(17,1), Pair(18,1), Pair(19,1),
                Pair(1,2), Pair(4,2), Pair(7,2), Pair(10,2), Pair(13,2), Pair(16,2)
        )

        val seaMonsterOffsets2 = mutableSetOf<Pair<Int, Int>>()
        for(offset in seaMonsterOffsets) {
            seaMonsterOffsets2.add(Pair(19-offset.first, offset.second))
        }

        val seaMonsterOffsets3 = mutableSetOf<Pair<Int, Int>>()
        for(offset in seaMonsterOffsets) {
            seaMonsterOffsets3.add(Pair(2-offset.second, offset.first))
        }

        val seaMonsterOffsets4 = mutableSetOf<Pair<Int, Int>>()
        for(offset in seaMonsterOffsets) {
            seaMonsterOffsets4.add(Pair(offset.second, 19-offset.first))
        }

        val seaMonsterOffsets5 = mutableSetOf<Pair<Int, Int>>()
        for(offset in seaMonsterOffsets) {
            seaMonsterOffsets5.add(Pair(19-offset.first, 2-offset.second))
        }

        val seaMonsterOffsets6 = mutableSetOf<Pair<Int, Int>>()
        for(offset in seaMonsterOffsets2) {
            seaMonsterOffsets6.add(Pair(19-offset.first, 2-offset.second))
        }

        val seaMonsterOffsets7 = mutableSetOf<Pair<Int, Int>>()
        for(offset in seaMonsterOffsets3) {
            seaMonsterOffsets7.add(Pair(2-offset.first, 19-offset.second))
        }

        val seaMonsterOffsets8 = mutableSetOf<Pair<Int, Int>>()
        for(offset in seaMonsterOffsets4) {
            seaMonsterOffsets8.add(Pair(2-offset.first, 19-offset.second))
        }

        var copy = image.toMutableMap()
        var maxX = 0
        var maxY = 0
        for(point in image.keys) {
            if(point.x > maxX) {
                maxX = point.x
            }
            if(point.y > maxY) {
                maxY = point.y
            }
        }

        for(x in 0 until maxX) {
            for(y in 0 until maxY) {

                // Monster Check 1
                var foundMonster = true
                for(offset in seaMonsterOffsets) {
                    val checkX = x+offset.first
                    val checkY = y+offset.second
                    if(image[Point2D(checkX, checkY)] == null || image[Point2D(checkX, checkY)] != '#') {
                        foundMonster = false
                        break
                    }
                }
                if(foundMonster) {
                    println("Found 1")
                    for (offset in seaMonsterOffsets) {
                        val checkX = x + offset.first
                        val checkY = y + offset.second
                        copy[Point2D(checkX, checkY)] = 'O'
                    }
                }

                // Monster Check 2
                foundMonster = true
                for(offset in seaMonsterOffsets2) {
                    val checkX = x+offset.first
                    val checkY = y+offset.second
                    if(image[Point2D(checkX, checkY)] == null || image[Point2D(checkX, checkY)] != '#') {
                        foundMonster = false
                        break
                    }
                }
                if(foundMonster) {
                    println("Found 1")
                    for (offset in seaMonsterOffsets2) {
                        val checkX = x + offset.first
                        val checkY = y + offset.second
                        copy[Point2D(checkX, checkY)] = 'O'
                    }
                }

                // Monster Check 3
                foundMonster = true
                for(offset in seaMonsterOffsets3) {
                    val checkX = x+offset.first
                    val checkY = y+offset.second
                    if(image[Point2D(checkX, checkY)] == null || image[Point2D(checkX, checkY)] != '#') {
                        foundMonster = false
                        break
                    }
                }
                if(foundMonster) {
                    println("Found 1")
                    for (offset in seaMonsterOffsets3) {
                        val checkX = x + offset.first
                        val checkY = y + offset.second
                        copy[Point2D(checkX, checkY)] = 'O'
                    }
                }

                // Monster Check 4
                foundMonster = true
                for(offset in seaMonsterOffsets4) {
                    val checkX = x+offset.first
                    val checkY = y+offset.second
                    if(image[Point2D(checkX, checkY)] == null || image[Point2D(checkX, checkY)] != '#') {
                        foundMonster = false
                        break
                    }
                }
                if(foundMonster) {
                    println("Found 1")
                    for (offset in seaMonsterOffsets4) {
                        val checkX = x + offset.first
                        val checkY = y + offset.second
                        copy[Point2D(checkX, checkY)] = 'O'
                    }
                }

                // Monster Check 5
                foundMonster = true
                for(offset in seaMonsterOffsets5) {
                    val checkX = x+offset.first
                    val checkY = y+offset.second
                    if(image[Point2D(checkX, checkY)] == null || image[Point2D(checkX, checkY)] != '#') {
                        foundMonster = false
                        break
                    }
                }
                if(foundMonster) {
                    println("Found 1")
                    for (offset in seaMonsterOffsets5) {
                        val checkX = x + offset.first
                        val checkY = y + offset.second
                        copy[Point2D(checkX, checkY)] = 'O'
                    }
                }

                // Monster Check 6
                foundMonster = true
                for(offset in seaMonsterOffsets6) {
                    val checkX = x+offset.first
                    val checkY = y+offset.second
                    if(image[Point2D(checkX, checkY)] == null || image[Point2D(checkX, checkY)] != '#') {
                        foundMonster = false
                        break
                    }
                }
                if(foundMonster) {
                    println("Found 1")
                    for (offset in seaMonsterOffsets6) {
                        val checkX = x + offset.first
                        val checkY = y + offset.second
                        copy[Point2D(checkX, checkY)] = 'O'
                    }
                }

                // Monster Check 7
                foundMonster = true
                for(offset in seaMonsterOffsets7) {
                    val checkX = x+offset.first
                    val checkY = y+offset.second
                    if(image[Point2D(checkX, checkY)] == null || image[Point2D(checkX, checkY)] != '#') {
                        foundMonster = false
                        break
                    }
                }
                if(foundMonster) {
                    println("Found 1")
                    for (offset in seaMonsterOffsets7) {
                        val checkX = x + offset.first
                        val checkY = y + offset.second
                        copy[Point2D(checkX, checkY)] = 'O'
                    }
                }

                // Monster Check 8
                foundMonster = true
                for(offset in seaMonsterOffsets8) {
                    val checkX = x+offset.first
                    val checkY = y+offset.second
                    if(image[Point2D(checkX, checkY)] == null || image[Point2D(checkX, checkY)] != '#') {
                        foundMonster = false
                        break
                    }
                }
                if(foundMonster) {
                    println("Found 1")
                    for (offset in seaMonsterOffsets8) {
                        val checkX = x + offset.first
                        val checkY = y + offset.second
                        copy[Point2D(checkX, checkY)] = 'O'
                    }
                }
            }
        }
        return copy
    }

    private fun rotateTileUntilMatchingEdges(tile: Tile, matchEdges: Set<Int>) {
        var matches = mutableMapOf<Int, Boolean>()
        for(i in matchEdges) {
            matches[i] = sumMatchingEdges(tile.edges[i], tile.id) > 0
        }
        var rotations = 0
        while(!matches.values.reduce{ acc, it -> acc && it}) {
            rotations++
            if(rotations >= 4) {
                throw Exception("Can't make tile fit by rotating")
            }
            tile.rotate90()
            for(i in matchEdges) {
                matches[i] = sumMatchingEdges(tile.edges[i], tile.id) > 0
            }
        }
    }

    fun productOfCornerIds() : Long {
        val ids = findCorners()
        var product = 1L
        for(id in ids) {
            product *= id
        }
        return product
    }

    fun solvePart2(): Int {
        createFullImage()
        var sum = 0
        for(char in finalImage.values) {
            if(char == '#') {
                sum++
            }
        }

        return sum
    }
}