@file:JvmName("Puzzle1")

package day13

fun main() {

    val timetable = BusTimetable(fileName = "src/main/resources/day13/input.txt")
    val waitDetails = timetable.earliestDeparture()
    println("The earliest bus you can get is ${waitDetails.first}, with a wait of ${waitDetails.second}.")
    println("The product of these is ${waitDetails.first * waitDetails.second}")
}