package day9

import java.io.File

class XmasCypherAttack(fileName: String) {

    val data : List<Long>

    init {
        val input = mutableListOf<Long>()
        File(fileName).forEachLine {
            input.add(it.toLong())
        }
        data = input
    }

    fun findFirstWeakness(preambleLength:Int) : Long {
        for(i in preambleLength until data.size - 1) {
            val target = data[i]
            val candidates = data.subList(i - preambleLength, i)
            if(!canBeSumOfTwoFrom(target, candidates)) return target
        }
        throw Exception("Couldn't find a weakness")
    }

    private fun canBeSumOfTwoFrom(target: Long, candidates: List<Long>) : Boolean {
        for (x in candidates.indices) {
            for (y in x + 1 until candidates.size) {
                if(candidates[x] + candidates[y] == target) {
                    return true
                }
            }
        }
        return false
    }
}
