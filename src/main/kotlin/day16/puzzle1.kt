@file:JvmName("Puzzle1")

package day16

fun main() {
    val translator = TicketTranslator(fileName = "src/main/resources/day16/input.txt")
    println("Sum of invalid fields is ${translator.sumOfInvalidFields()}")
}