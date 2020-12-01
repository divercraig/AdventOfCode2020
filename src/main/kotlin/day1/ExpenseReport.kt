package day1

import java.io.File

class ExpenseReport(fileName: String){
    private val values: List<Int>

    init {
        val expenseReport = mutableListOf<Int>()
        File(fileName).forEachLine { expenseReport.add(it.toInt())}
        values = expenseReport
    }

    fun findSpecificProduct(): Int {
        val targetSum = 2020
        values.forEach {
            val partnerValue = targetSum - it
            if(values.contains(partnerValue)) return it * partnerValue
        }
        throw Exception("Didn't find two values which summed to $targetSum")
    }
}