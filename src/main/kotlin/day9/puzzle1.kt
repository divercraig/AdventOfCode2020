@file:JvmName("Puzzle1")

package day9

fun main() {

    val attack = XmasCypherAttack("src/main/resources/day9/input.txt")
    val target = attack.findVulnerabilityTarget(25)

    println("The vulnerability target detected is $target")
}