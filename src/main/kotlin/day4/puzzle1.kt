@file:JvmName("Puzzle1")

package day4

fun main() {
    val loader = PassportLoader("src/main/resources/day4/input.txt")

    try {
        println("There are ${loader.countPassportsWithRequiredFields()} valid passports")
    } catch (e: Exception) {
        println("Exception occured: ${e.message}")
    }
}