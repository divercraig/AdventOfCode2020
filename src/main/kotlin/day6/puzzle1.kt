@file:JvmName("Puzzle1")

package day6

fun main() {

    val planeCustomsFormReader = PlaneCustomsFormReader("src/main/resources/day6/input.txt")

    println("The Sum of Yes answers is ${planeCustomsFormReader.sumOfYesAnswers}")
}