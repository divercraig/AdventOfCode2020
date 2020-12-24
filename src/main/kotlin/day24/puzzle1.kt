@file:JvmName("Puzzle1")

package day24

fun main() {
    val grid = HexGrid(fileName = "src/main/resources/day24/input.txt")
    println ("There should be ${grid.blackCells()} black cells")
}