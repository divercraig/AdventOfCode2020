@file:JvmName("Puzzle1")

package day11

fun main() {

    val seatMap = SeatMap(fileName = "src/main/resources/day11/input.txt")

    println("After the chaos there are ${seatMap.seatsOccupiedAfterChaos()} occupied seats")
}