@file:JvmName("Puzzle2")

package day5

import java.io.File

fun main() {
    var seatIds = ArrayList<Int>()

    File("src/main/resources/day5/puzzle1/input.txt").forEachLine {
        val seat = Seat(it)
        seatIds.add(seat.seatId)
    }

    seatIds.sort()

    for(id in seatIds) {
        if(!seatIds.contains(id+1) && seatIds.contains(id+2)) {
            println("There is an empty space at ${id+1}")
        }
    }

}