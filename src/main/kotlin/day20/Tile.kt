package day20

import util.Point2D

class Tile(input: List<String>) {
    val id: Int
    val pixels: Map<Point2D, Char>
    val edgeLength: Int
    val edges: List<String>

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


}