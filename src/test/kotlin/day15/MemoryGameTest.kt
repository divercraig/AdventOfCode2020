package day15

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MemoryGameTest {

    private fun solveFor(startingNumbers:List<Int>, expectedNumber:Int) {
        val memoryGame = MemoryGame(startingNumbers)
        assertEquals(expectedNumber, memoryGame.solveFor(2020), "Failed to solve for ${startingNumbers}")
    }

    @Test
    internal fun testSolveFor1() {
        solveFor(listOf(0,3,6), 436)
    }

    @Test
    internal fun testSolveFor2() {
        solveFor(listOf(1,3,2), 1)
    }

    @Test
    internal fun testSolveFor3() {
        solveFor(listOf(2,1,3), 10)
    }

    @Test
    internal fun testSolveFor4() {
        solveFor(listOf(1,2,3), 27)
    }

    @Test
    internal fun testSolveFor5() {
        solveFor(listOf(2,3,1), 78)
    }

    @Test
    internal fun testSolveFor6() {
        solveFor(listOf(3,2,1), 438)
    }

    @Test
    internal fun testSolveFor7() {
        solveFor(listOf(3,1,2), 1836)
    }
}