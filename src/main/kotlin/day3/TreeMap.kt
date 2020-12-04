package day3

import java.io.File

class TreeMap(fileName: String) {

    private val map: Array<BooleanArray>
    private val mapWidth: Int
    private val mapLength: Int

    init {
        val columns = ArrayList<BooleanArray>()
        File(fileName).forEachLine {
            val row = BooleanArray(it.trim().length)
            for(i in it.indices) {
                if(it[i] == '#') row[i] = true else row[i] = false
            }
            columns.add(row)
        }
        mapLength = columns.size
        mapWidth = columns[0].size
        map = columns.toTypedArray()
    }

    private fun treeAtPosition(x:Int, y:Int): Boolean {
        return map[y][x]
    }

    fun howManyTreesOnPath(x: Int, y:Int): Int {
        var xPosition = 0
        var yPosition = 0
        var treesEncountered = 0

        do {
            if(treeAtPosition(xPosition, yPosition)) treesEncountered++

            xPosition += x
            if(xPosition >= mapWidth) xPosition -= mapWidth
            yPosition += y

        } while (yPosition < mapLength)

        return treesEncountered
    }
}