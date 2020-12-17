package day17

import java.io.File

class ConwayHyperCube(fileName: String) {
    var bootSector = mutableMapOf<HyperPosition, Boolean>()
    private var minX = 0
    private var maxX: Int
    private var minY = 0
    private var maxY: Int
    private var minZ = 0
    private var maxZ = 0
    private var minW = 0
    private var maxW = 0

    init {
        var x = 0
        var y = 0
        File(fileName).forEachLine {
            x = 0
            for (char in it) {
                bootSector[HyperPosition(x, y, 0,0)] = char == '#'
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

    private fun neighbours(cell: HyperPosition): Set<HyperPosition> {
        val neighbours = mutableSetOf<HyperPosition>()
        for (xOffset in -1..1) {
            for (yOffset in -1..1) {
                for (zOffset in -1..1) {
                    for(wOffset in -1..1) {
                        if (xOffset == 0 && yOffset == 0 && zOffset == 0 && wOffset == 0) {
                            continue
                        }
                        neighbours.add(
                                HyperPosition(
                                        cell.first + xOffset,
                                        cell.second + yOffset,
                                        cell.third + zOffset,
                                        cell.forth + wOffset
                                )
                        )
                    }
                }
            }
        }
        return neighbours
    }

    private fun activeNeighbours(cell: HyperPosition): Int {
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
            maxW++
            minW--

            val newLayout = mutableMapOf<HyperPosition, Boolean>()
            for (x in minX..maxX) {
                for (y in minY..maxY) {
                    for (z in minZ..maxZ) {
                        for(w in minW..maxW) {
                            val cell = HyperPosition(x, y, z, w)
                            val activeNeighbours = activeNeighbours(cell)

                            if (bootSector[cell] != null && bootSector[cell]!!) {
                                newLayout[cell] = !(activeNeighbours != 2 && activeNeighbours != 3)
                            } else {
                                newLayout[cell] = activeNeighbours == 3
                            }
                        }
                    }
                }
            }
            bootSector = newLayout
        }

        return activeCells()
    }
}