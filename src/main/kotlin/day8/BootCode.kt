package day8

import java.io.File
import java.lang.Exception

class BootCode(instructionFile: String) {
    private val NOP = "nop"
    private val ACC = "acc"
    private val JMP = "jmp"

    private var instructions: List<Pair<String, Int>>
    private val originalInstruction: List<Pair<String, Int>>
    private val instructionsVisited = mutableListOf<Int>()
    private val instructionsChanged = mutableSetOf<Int>()
    private var instructionPointer = 0
    private var accumulator = 0


    init {
        val inst = mutableListOf<Pair<String, Int>>()
        File(instructionFile).forEachLine {
            val (operation, argument) = it.split(" ")
            inst.add(Pair(operation, argument.toInt()))
        }
        instructions = inst.toMutableList()
        originalInstruction = inst.toList()
    }

    private fun resetState() {
        accumulator = 0
        instructionPointer = 0
        instructionsVisited.clear()
    }

    fun runUntilLoop(): Int {
        if(runProgram()) {
            throw Exception("Expected a loop, but none detected. Program ended normally")
        } else {
            return accumulator
        }
    }

    fun runProgram(): Boolean {
        resetState()
        while(instructionPointer < instructions.size) {
            if (instructionsVisited.contains(instructionPointer)) {
                return false // looped
            }
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

        return true // completed successfully
    }

    fun runSelfHealingProgram(): Int {
        var looped = !runProgram()
        val originalExecutionOrder = instructionsVisited.toList()
        var flippedExecutionIndex = -1
        while (looped) {
            instructions = originalInstruction.toMutableList()
            flippedExecutionIndex++
            val flippedInstructionPointer = originalExecutionOrder[flippedExecutionIndex]
            when(instructions[flippedInstructionPointer].first) {
                JMP -> {
                    (instructions as MutableList<Pair<String, Int>>)[flippedInstructionPointer] =
                            Pair(NOP, instructions[flippedInstructionPointer].second)
                    looped = !runProgram()
                }
                NOP -> {
                    (instructions as MutableList<Pair<String, Int>>)[flippedInstructionPointer] =
                            Pair(JMP, instructions[flippedInstructionPointer].second)
                    looped = !runProgram()
                }
            }
        }
        return accumulator;
    }
}