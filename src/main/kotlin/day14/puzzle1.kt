@file:JvmName("Puzzle1")

package day14

@ExperimentalStdlibApi
fun main() {

    val comp = DockingComputer(fileName = "src/main/resources/day14/input.txt")
    println("Sum of memory is ${comp.runProgram()}")
}