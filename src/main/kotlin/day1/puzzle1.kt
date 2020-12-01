@file:JvmName("Puzzle1")

package day1

import java.io.File

fun main() {
    val expenseReport = readExpenseReport("src/main/resources/day1/puzzle1/input.txt")

    try {
        println("Specific Product of Key Values is ${findSpecificProduct(expenseReport)}")
    } catch (e: Exception) {
        println("Exception occured: ${e.message}")
    }
}

fun readExpenseReport(fileName: String): List<Int> {
    val expenseReport = mutableListOf<Int>()
    File(fileName).forEachLine { expenseReport.add(it.toInt())}
    return expenseReport
}

fun findSpecificProduct(expenseReport: List<Int>): Int {
    val targetSum = 2020
    expenseReport.forEach {
        val partnerValue = targetSum - it
        if(expenseReport.contains(partnerValue)) return it * partnerValue
    }
    throw Exception("Didn't find two values which summed to $targetSum")
}