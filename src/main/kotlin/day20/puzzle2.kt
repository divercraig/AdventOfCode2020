@file:JvmName("Puzzle2")

package day20

fun main() {
    val matcher = TileMatcher(fileName = "src/main/resources/day20/input.txt")
    val answer = matcher.solvePart2()
    println("The answer is $answer")
}