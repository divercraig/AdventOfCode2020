@file:JvmName("Puzzle2")
package day2

import java.io.File

fun main() {
    var validPasswords = 0
    File("src/main/resources/day2/puzzle1/input.txt").forEachLine {
        if (PasswordCheck(it).passwordPositionsAreValid()) {
            validPasswords++
        }
    }

    println("There are ${validPasswords} valid passwords using the position logic in the input")
}