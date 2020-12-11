@file:JvmName("Puzzle2")

package day11

fun main() {

    val seatMap = SeatMap(fileName = "src/main/resources/day11/input.txt")

    println("After the chaos there are really ${seatMap.seatsOccupiedWithRealPeople()} occupied seats")
}