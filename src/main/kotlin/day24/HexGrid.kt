package day24

import java.io.File

class HexGrid(fileName: String) {
    var cells = mutableMapOf<HexCell, Char>()
    var maxX=0
    var maxY=0
    var minX=0
    var minY=0

    init {
        File(fileName).forEachLine {
            val cell = HexCell(it)

            if(cell.x > maxX) {
                maxX = cell.x
            }
            if(cell.x < minX) {
                minX = cell.x
            }
            if(cell.y > maxY) {
                maxY = cell.y
            }
            if(cell.y < minY) {
                minY = cell.y
            }

            if(cells.keys.contains(cell)) {
                if(cells[cell] == 'B') {
                    cells[cell] = 'W'
                } else {
                    cells[cell] = 'B'
                }
            } else {
                cells[cell] = 'B'
            }
        }
    }

    fun blackCells() : Int {
        var sum = 0
        for(cellColour in cells.values) {
            if(cellColour == 'B') {
                sum++
            }
        }
        return sum
    }

    fun processXDays(days: Int) {
        repeat(days) {
            processOneDay()
        }
    }

    fun processOneDay() {
        var copyOfFloor = cells.toMutableMap()
        maxX += 2
        minX -= 2
        maxY++
        minY--

        for(x in minX..maxX) {
            for(y in minY..maxY) {
                val cell  = HexCell(x,y)
                var cellColor = cells[cell] ?: 'W'

                var blackAdjacentCells = 0
                for(adjacentCell in cell.adjacentCells()) {
                    if(cells[adjacentCell] == 'B') {
                        blackAdjacentCells++
                    }
                }

                if(cellColor == 'B' && (blackAdjacentCells ==0 || blackAdjacentCells >2)) {
                    copyOfFloor[cell] = 'W'
                }
                if(cellColor == 'W' && blackAdjacentCells == 2) {
                    copyOfFloor[cell] = 'B'
                }
            }
        }
        cells = copyOfFloor
    }
}