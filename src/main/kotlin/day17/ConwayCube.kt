package day17

import java.io.File

class ConwayCube(fileName: String) {
    var bootSector = mutableMapOf<Triple<Int, Int, Int>, Boolean>()
    private var minX = 0
    private var maxX: Int
    private var minY = 0
    private var maxY: Int
    private var minZ = 0
    private var maxZ = 0

    init {
        var x = 0
        var y = 0
        File(fileName).forEachLine {
            x = 0
            for (char in it) {
                bootSector[Triple(x, y, 0)] = char == '#'
                x++
            }
            y++
        }
        maxY = y - 1
        maxX = x - 1

    }

    private fun activeCells(): Int {
        var active = 0
        for (value in bootSector.values) {
            if (value) {
                active++
            }
        }
        return active
    }

    private fun neighbours(cell: Triple<Int, Int, Int>): Set<Triple<Int, Int, Int>> {
        val neighbours = mutableSetOf<Triple<Int, Int, Int>>()
        for (xOffset in -1..1) {
            for (yOffset in -1..1) {
                for (zOffset in -1..1) {
                    if (xOffset == 0 && yOffset == 0 && zOffset == 0) {
                        continue
                    }
                    neighbours.add(
                            Triple(
                                    cell.first + xOffset,
                                    cell.second + yOffset,
                                    cell.third + zOffset
                            )
                    )
                }
            }
        }
        return neighbours
    }

    private fun activeNeighbours(cell: Triple<Int, Int, Int>): Int {
        var active = 0
        for (neighbour in neighbours(cell)) {
            if (bootSector[neighbour] != null && bootSector[neighbour]!!) {
                active++
            }
        }
        return active
    }


    fun bootCube(phases: Int = 6): Int {
        for (phase in 0 until phases) {
            maxZ++
            minZ--
            maxY++
            minY--
            maxX++
            minX--

            val newLayout = mutableMapOf<Triple<Int, Int, Int>, Boolean>()
            for (x in minX..maxX) {
                for (y in minY..maxY) {
                    for (z in minZ..maxZ) {
                        val cell = Triple(x, y, z)
                        val activeNeighbours = activeNeighbours(cell)

                        if (bootSector[cell] != null && bootSector[cell]!!) {
                            newLayout[cell] = !(activeNeighbours != 2 && activeNeighbours != 3)
                        }
                        else {
                            newLayout[cell] = activeNeighbours == 3
                        }
                    }
                }
            }
            bootSector = newLayout
        }

        return activeCells()
    }
}