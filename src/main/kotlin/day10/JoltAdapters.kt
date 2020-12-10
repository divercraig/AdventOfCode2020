package day10

import java.io.File

class JoltAdapters(fileName: String) {
    val adapters = mutableListOf<Int>()

    init {
        var max = 0
        File(fileName).forEachLine {
            val jolts = it.toInt()
            if(jolts > max) {
                max = jolts
            }
            adapters.add(jolts)
        }
        adapters.add(0)
        adapters.add(max + 3)
        adapters.sort()
    }

    fun joltDifferenceCalc() : Int {
        var differencesOf1 = 0
        var differencesOf3 = 0

        for(i in 1 until adapters.size) {
            val diff = adapters[i] - adapters[i-1]
            when(diff) {
                1 -> differencesOf1++
                3 -> differencesOf3++
            }
        }
        return differencesOf1 * differencesOf3
    }
}