@file:JvmName("Puzzle1")

package day20

fun main() {
    val matcher = TileMatcher(fileName = "src/main/resources/day20/input.txt")
    println("Product of corners is ${matcher.productOfCornerIds()}")
}