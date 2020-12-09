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

    fun findVulnerabilityTarget(preambleLength:Int) : Long {
        for(i in preambleLength until data.size - 1) {
            val target = data[i]
            val candidates = data.subList(i - preambleLength, i)
            if(!canBeSumOfTwoFrom(target, candidates)) return target
        }
        throw Exception("Couldn't find a vulnerability target")
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

    fun findWeakness(preambleLength: Int) : Long? {
        val vulnerabilityTarget = this.findVulnerabilityTarget(preambleLength)
        val indices = findContiguousSetIndices(vulnerabilityTarget)
        val vulnerableList = data.subList(indices.first, indices.second)
        return vulnerableList.min()?.plus(vulnerableList.max()!!)
    }

    fun findContiguousSetIndices(target: Long) : Pair<Int, Int> {
        for (x in data.indices) {
            var sum = data[x]
            for (y in x + 1 until data.size) {
                sum += data[y]
                if(sum == target) {
                    return Pair(x,y)
                }
                if(sum > target) {
                    break
                }
            }
        }

        throw Exception("Couldn't find contiguousSet")
    }
}
