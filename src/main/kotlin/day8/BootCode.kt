package day8

import java.io.File

class BootCode(instructionFile: String) {
    private val NOP = "nop"
    private val ACC = "acc"
    private val JMP = "jmp"

    private val instructions: List<Pair<String, Int>>
    private val instructionsVisited = mutableSetOf<Int>()
    private var instructionPointer = 0
    private var accumulator = 0


    init {
        val inst = mutableListOf<Pair<String, Int>>()
        File(instructionFile).forEachLine {
            val (operation, argument) = it.split(" ")
            inst.add(Pair(operation, argument.toInt()))
        }
        instructions = inst
    }

    fun runUntilLoop(): Int {
        while(!instructionsVisited.contains(instructionPointer)) {
            instructionsVisited.add(instructionPointer)

            when(instructions[instructionPointer].first) {
                NOP -> instructionPointer++
                ACC -> {
                    accumulator+= instructions[instructionPointer].second
                    instructionPointer++
                }
                JMP -> instructionPointer+= instructions[instructionPointer].second
            }
        }

        return accumulator
    }
}