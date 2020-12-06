@file:JvmName("Puzzle2")

package day6

fun main() {
    val planeCustomsFormReader = PlaneCustomsFormReader("src/main/resources/day6/puzzle1/input.txt")

    println("The Sum Of Group Common Yes Answers is ${planeCustomsFormReader.sumOfGroupCommonYesAnswers}")

}