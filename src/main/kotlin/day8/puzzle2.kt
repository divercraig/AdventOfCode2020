@file:JvmName("Puzzle2")

package day8

fun main() {

    val bootCode = BootCode("src/main/resources/day8/input.txt")
    val accumulator = bootCode.runSelfHealingProgram()

    println("When the code is fixed the result accumulator value is $accumulator")
}