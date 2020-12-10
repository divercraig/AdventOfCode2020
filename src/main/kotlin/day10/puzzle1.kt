@file:JvmName("Puzzle1")

package day10

fun main() {

    val adapters = JoltAdapters(fileName = "src/main/resources/day10/input.txt")

    println("The joltage difference calculation is ${adapters.joltDifferenceCalc()}")
}