@file:JvmName("Puzzle2")

package day9

fun main() {

    val attack = XmasCypherAttack("src/main/resources/day9/input.txt")
    val target = attack.findWeakness(25)

    println("The weakness is $target")
}