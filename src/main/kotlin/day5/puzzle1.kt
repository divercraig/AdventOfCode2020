@file:JvmName("Puzzle1")

package day5

import java.io.File

fun main() {
    var highestSeatId = 0

    File("src/main/resources/day5/input.txt").forEachLine {
        val seat = Seat(it)
        if(seat.seatId > highestSeatId) {
            highestSeatId = seat.seatId
        }
    }

    println("The highest seat id is $highestSeatId")
}