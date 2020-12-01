@file:JvmName("Puzzle2")

package day1

fun main() {
    val expenseReport = ExpenseReport("src/main/resources/day1/puzzle1/input.txt")

    try {
        println("Specific Product of Key Values is ${expenseReport.findProductOf3TargetValues()}")
    } catch (e: Exception) {
        println("Exception occured: ${e.message}")
    }
}