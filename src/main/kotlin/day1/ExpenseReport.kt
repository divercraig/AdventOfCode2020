package day1

import java.io.File

class ExpenseReport(fileName: String){
    private val originalValues: List<Int>
    private val targetSum = 2020


    init {
        val expenseReport = mutableListOf<Int>()
        File(fileName).forEachLine { expenseReport.add(it.toInt())}
        originalValues = expenseReport
    }

    fun findProductOf2TargetValues(target: Int = targetSum, values: List<Int> = originalValues): Int {
        values.forEach {
            val partnerValue = target - it
            if(values.contains(partnerValue)) return it * partnerValue
        }
        throw Exception("Didn't find two values which summed to $target")
    }

    fun findProductOf3TargetValues(values: List<Int> = originalValues): Int {
        val head = values.first()
        val tail = values.subList(1, values.size)
        var newTarget = targetSum - head
        try {
            val productOfRemaining2Values = findProductOf2TargetValues(target = newTarget, values = tail)
            return productOfRemaining2Values * head
        } catch (e: Exception) {
            if(tail.size < 3) {
                throw Exception("Ran out of values to find 3 which sum to the target")
            }
            return findProductOf3TargetValues(values = tail)
        }
    }
}