package day13

import java.io.File

class BusTimetable(fileName: String) {
    val arrivalTime: Int
    val departureIntervals = mutableListOf<Int>()
    val busLines = mutableListOf<Pair<Int, Long>>() //index, departure frequency


    init {
        val lines = File(fileName).readLines()
        arrivalTime = lines[0].toInt()
        for(value in lines[1].split(',')) {
            if(value != "x") {
                departureIntervals.add(value.toInt())
            }
        }
        lines[1].split(",").forEachIndexed {
            index, it -> if (it != "x") busLines.add(Pair(index,it.toLong()))
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

    fun competitionTime() : Long {
        // Use Fold rather than reduce because we are operating on a list of Pairs, but want to return a long
        // ProductOfAllFrequencies sets an upper bound for times - we shouldn't go past this number
        val productOfAllFrequencies = busLines.fold(1L, { acc, int -> acc*int.second })

        var time = busLines.first().second

        // Repeat for each bus finding a time which meets the requirements for this bus + the next bus
        for (i in 1 until busLines.size) {
            val busesToI = busLines.take(i)
            val productOfFrequenciesToI = busesToI.fold(1L, {acc, it -> acc * it.second})

            // Step from the current time in steps equal to the current bus frequency
            // Use the first time in the sequence which meets the requirement for the next bus i.e. remainder matches the index
            time = (time..productOfAllFrequencies step productOfFrequenciesToI).first { (it + busLines[i].first) % busLines[i].second == 0L }
        }

        return time
    }

}