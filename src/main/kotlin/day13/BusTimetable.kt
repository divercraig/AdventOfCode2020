package day13

import java.io.File

class BusTimetable(fileName: String) {
    val arrivalTime: Int
    val departureIntervals = mutableListOf<Int>()

    init {
        val lines = File(fileName).readLines()
        arrivalTime = lines[0].toInt()
        for(value in lines[1].split(',')) {
            if(value != "x") {
                departureIntervals.add(value.toInt())
            }
        }
    }

    fun earliestDeparture() : Pair<Int, Int> {
        var earliestDeparture = Pair(-1, Int.MAX_VALUE)
        for(bus in departureIntervals) {
            val missedBy = arrivalTime % bus
            val waitTime = bus - missedBy
            if(waitTime < earliestDeparture.second) {
                earliestDeparture = Pair(bus, waitTime)
            }
        }
        return earliestDeparture
    }

}