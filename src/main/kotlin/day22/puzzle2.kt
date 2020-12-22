@file:JvmName("Puzzle2")

package day22

@ExperimentalStdlibApi
fun main() {
    val combat = Combat("src/main/resources/day22/input.txt")
    combat.playRecursive()
    println ("Final score after recursion is ${combat.score()}")
}