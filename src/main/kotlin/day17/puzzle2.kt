@file:JvmName("Puzzle2")

package day17

fun main() {
    val cube = ConwayHyperCube(fileName = "src/main/resources/day17/input.txt")
    println("After booting up there are ${cube.bootCube()} active cells in the HYPER CUBE")
}