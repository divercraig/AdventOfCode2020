@file:JvmName("Puzzle2")

package day24

fun main() {
    val grid = HexGrid(fileName = "src/main/resources/day24/input.txt")
    grid.processXDays(100)
    println ("There should be ${grid.blackCells()} black cells after 100 days")
}