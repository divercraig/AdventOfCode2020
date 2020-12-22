@file:JvmName("Puzzle1")

package day22

@ExperimentalStdlibApi
fun main() {
    val combat = Combat("src/main/resources/day22/input.txt")
    combat.play()
    println ("Final score is ${combat.score()}")
}