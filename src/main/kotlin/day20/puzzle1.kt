@file:JvmName("Puzzle1")

package day20

import day18.newMath.evaluateWithNewMath
import java.io.File
import java.math.BigInteger

fun main() {
    val matcher = TileMatcher(fileName = "src/main/resources/day20/input.txt")
    println("Product of corners is ${matcher.rearrange()}")
}