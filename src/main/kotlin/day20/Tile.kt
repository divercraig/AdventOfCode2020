package day20

import util.Point2D

class Tile(input: List<String>) {
    val id: Int
    var pixels: Map<Point2D, Char>
    val edgeLength: Int
    var edges: List<String>

    private fun defineEdges() :List<String> {
        val edges = mutableListOf<String>()
        var top = ""
        var bottom = ""
        var left = ""
        var right = ""
        for( i in 0 until edgeLength) {
            top += pixels[Point2D(i, 0)]
            right += pixels[Point2D(edgeLength - 1, i)]
            bottom += pixels[Point2D(i, edgeLength - 1)]
            left += pixels[Point2D(0, i)]
        }
        edges.add(top)
        edges.add(right)
        edges.add(bottom)
        edges.add(left)

        return edges
    }

    init{
        val pixelReader = mutableMapOf<Point2D, Char>()
        var titleLine = input[0]
        var dirtyId = titleLine.split(' ')[1]
        id = dirtyId.removeSuffix(":").toInt()

        var y = 0
        for(i in 1 until input.size) {
            var x = 0
            for(char in input[i]) {
                pixelReader[Point2D(x,y)] = char
                x++
            }
            y++
        }
        pixels = pixelReader
        edgeLength = y
        edges = defineEdges()
    }

    fun rotate90() {
        val newPixels = mutableMapOf<Point2D, Char>()
        for(x in 0 until edgeLength) {
            for(y in 0 until edgeLength) {
                val originPoint = Point2D(x, y)
                val originValue = pixels[originPoint]!!
                val newPoint = Point2D(y, (edgeLength-1)-x)
                newPixels[newPoint] = originValue
            }
        }
        pixels = newPixels
        edges = defineEdges()
    }

    fun flipVertical() {
        val newPixels = mutableMapOf<Point2D, Char>()
        for(x in 0 until edgeLength) {
            for(y in 0 until edgeLength) {
                val originalPoint = Point2D(x, y)
                val originalValue = pixels[originalPoint]!!
                val newPoint = Point2D(x, (edgeLength-1)-y)
                newPixels[newPoint] = originalValue
            }
        }
        pixels = newPixels
        edges = defineEdges()
    }

    fun moveUntilEdgeMatches(edge: Int, matches: String) {
        var rotations = 0
        while(rotations < 4) {
            rotate90()
            rotations++
            if(edges[edge] == matches) return
        }
        rotations = 0
        flipVertical()
        while(rotations < 4) {
            rotate90()
            rotations++
            if(edges[edge] == matches) return
        }
        flipVertical()
        throw Exception("COULD'T MAKE TILE MATCH")
    }


}