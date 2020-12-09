@file:JvmName("Puzzle1")

package day9

fun main() {

    val attack = XmasCypherAttack("src/main/resources/day9/input.txt")
    val weakness = attack.findFirstWeakness(25)

    println("The value of the first weakness detected is $weakness")
}