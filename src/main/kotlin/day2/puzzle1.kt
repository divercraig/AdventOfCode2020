@file:JvmName("Puzzle1")
package day2

import java.io.File

fun main() {
    var validPasswords = 0
    File("src/main/resources/day2/input.txt").forEachLine {
        if (PasswordCheck(it).passwordIsValid()) {
            validPasswords++
        }
    }

    println("There are ${validPasswords} valid passwords in the input")
}