@file:JvmName("Puzzle1")

package day17

fun main() {
    val cube = ConwayCube(fileName = "src/main/resources/day17/input.txt")
    println("After booting up there are ${cube.bootCube()} active cells")
}