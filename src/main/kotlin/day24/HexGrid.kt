package day24

import java.io.File

class HexGrid(fileName: String) {
    var blackCells = mutableSetOf<HexCell>()

    init {
        File(fileName).forEachLine {
            val cell = HexCell(it)
            if(blackCells.contains(cell)) {
                blackCells.remove(cell)
            } else {
                blackCells.add(cell)
            }
        }
    }
}