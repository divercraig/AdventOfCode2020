package day10

import java.io.File
import java.math.BigInteger

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

    fun numberOfValidArrangements(index: Int = 0, memoizedResults: LongArray = LongArray(adapters.size)):Long {
        if(memoizedResults[index] != 0L) {
            return memoizedResults[index]
        }

        if(index + 1 == adapters.size) {
            return 1
        }

        var sum = 0L
        for (offset in 1..3) {
            if(index + offset >= adapters.size) {
                break // prevent index out of bounds
            }

            if(adapters[index + offset] - adapters[index] <= 3) {
                sum += numberOfValidArrangements(index + offset, memoizedResults)
            }
        }

        memoizedResults[index] = sum
        return sum
    }

}