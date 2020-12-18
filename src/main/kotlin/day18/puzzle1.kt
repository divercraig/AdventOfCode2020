@file:JvmName("Puzzle1")

package day18

import day18.newMath.evaluateWithNewMath
import java.io.File
import java.math.BigInteger

fun main() {
    var sum = BigInteger.ZERO
    File("src/main/resources/day18/input.txt").forEachLine {
        sum += evaluateWithNewMath(it)
        println("Running total is $sum")
    }
    println("The sum of all equations with NEW MATH is $sum")
}