@file:JvmName("Puzzle1")

package day23

fun main() {
    val input = listOf(6,8,5,9,7,4,2,1,3)
    val circle = CupCircle(input)
    circle.playRounds(100)
    println ("After 100 rounds the final state is: ${circle.finalState()}")
}