@file:JvmName("Puzzle1")

package day4

fun main() {
    val loader = PassportLoader("src/main/resources/day4/puzzle1/input.txt")

    try {
        println("There are ${loader.countValidPassports()} valid passports")
    } catch (e: Exception) {
        println("Exception occured: ${e.message}")
    }
}