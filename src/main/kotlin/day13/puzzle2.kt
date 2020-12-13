@file:JvmName("Puzzle2")

package day13

fun main() {

    val timetable = BusTimetable(fileName = "src/main/resources/day13/input.txt")
    val answer = timetable.competitionTime()

    println("The earliest time for the competition is $answer")
}