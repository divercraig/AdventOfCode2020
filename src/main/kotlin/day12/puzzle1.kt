@file:JvmName("Puzzle1")

package day12

fun main() {

    val nav = Navigation(fileName = "src/main/resources/day12/input.txt")

    println("After ths instructions, the ship is  ${nav.distanceAfterNav()} from the start location")
}