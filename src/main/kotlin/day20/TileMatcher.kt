package day20

import java.io.File

class TileMatcher(fileName: String) {
    val tileMap: Map<Int, Tile>
    val edgeMap = mutableMapOf<String, MutableSet<Pair<Int, Int>>>()
    val reversedEdgeMap = mutableMapOf<String, MutableSet<Pair<Int, Int>>>()

    init {
        val mutableTileMap = mutableMapOf<Int, Tile>()

        val reader = File(fileName).bufferedReader()
        var currentTile = mutableListOf<String>()
        var line = ""

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

    fun rearrange() : Long {
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
        println ("minimum Ids are ${ids.toString()}")
        var product = 1L
        for(id in ids) {
            product *= id
        }
        return product
    }
}