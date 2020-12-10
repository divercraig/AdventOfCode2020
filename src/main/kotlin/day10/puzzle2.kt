@file:JvmName("Puzzle2")

package day10

fun main() {

    val adapters = JoltAdapters(fileName = "src/main/resources/day10/input.txt")

    println("The number of valid combinations is ${adapters.numberOfValidArrangements()}")
}