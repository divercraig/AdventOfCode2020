@file:JvmName("Puzzle2")

package day23

fun main() {
    val input = mutableListOf(6,8,5,9,7,4,2,1,3)
    val circle = LinkedCupCircle(input)
    circle.playRounds(10000000)
    println ("After 10 million rounds the final state is: ${circle.finalState()}")
}