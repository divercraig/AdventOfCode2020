@file:JvmName("Puzzle2")

package day18

import day18.advancedNewMath.evaluateWithAdvancedNewMath
import java.io.File
import java.math.BigInteger

fun main() {
    var sum = BigInteger.ZERO
    File("src/main/resources/day18/input.txt").forEachLine {
        sum += evaluateWithAdvancedNewMath(it)
        println("Running total is $sum")
    }
    println("The sum of all equations with ADVANCED NEW MATH is $sum")
}