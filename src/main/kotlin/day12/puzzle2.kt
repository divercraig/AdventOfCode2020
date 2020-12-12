@file:JvmName("Puzzle2")

package day12

fun main() {

    val nav = Navigation(fileName = "src/main/resources/day12/input.txt")

    println("After ths waypointing, the ship is  ${nav.distanceAfterWaypointing()} from the start location")
}