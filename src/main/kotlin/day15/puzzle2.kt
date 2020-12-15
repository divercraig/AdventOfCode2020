@file:JvmName("Puzzle2")

package day15

fun main() {
    val input = listOf(0,20,7,16,1,18,15)
    val game = MemoryGame(input)
    val result = game.solveFor(30000000)
    println("Result is $result")
}