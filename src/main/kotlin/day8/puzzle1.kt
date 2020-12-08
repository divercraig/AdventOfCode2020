@file:JvmName("Puzzle1")

package day8

fun main() {

    val bootCode = BootCode("src/main/resources/day8/input.txt")
    val accumulator = bootCode.runUntilLoop()

    println("When the code starts to loop the accumulator value is $accumulator")
}