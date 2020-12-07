@file:JvmName("Puzzle1")

package day1

fun main() {
    val expenseReport = ExpenseReport("src/main/resources/day1/input.txt")

    try {
        println("Specific Product of Key Values is ${expenseReport.findProductOf2TargetValues()}")
    } catch (e: Exception) {
        println("Exception occured: ${e.message}")
    }
}