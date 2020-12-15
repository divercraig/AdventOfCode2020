package day15

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MemoryGameTest {

    private fun solveFor(startingNumbers:List<Int>, turns:Int, expectedNumber:Int) {
        val memoryGame = MemoryGame(startingNumbers)
        assertEquals(expectedNumber, memoryGame.solveFor(turns), "Failed to solve for ${startingNumbers} after $turns turns")
    }

    @Test
    internal fun testSolveFor1() {
        solveFor(listOf(0,3,6), 2020,436)
    }

    @Test
    internal fun testSolveFor2() {
        solveFor(listOf(1,3,2), 2020, 1)
    }

    @Test
    internal fun testSolveFor3() {
        solveFor(listOf(2,1,3), 2020, 10)
    }

    @Test
    internal fun testSolveFor4() {
        solveFor(listOf(1,2,3), 2020, 27)
    }

    @Test
    internal fun testSolveFor5() {
        solveFor(listOf(2,3,1),2020, 78)
    }

    @Test
    internal fun testSolveFor6() {
        solveFor(listOf(3,2,1),2020, 438)
    }

    @Test
    internal fun testSolveFor7() {
        solveFor(listOf(3,1,2), 2020,1836)
    }

    @Test
    internal fun testSolveForBig1() {
        solveFor(listOf(0,3,6), 30000000,175594)
    }

    @Test
    internal fun testSolveForBig2() {
        solveFor(listOf(1,3,2), 30000000, 2578)
    }

    @Test
    internal fun testSolveForBig3() {
        solveFor(listOf(2,1,3), 30000000, 3544142)
    }

    @Test
    internal fun testSolveForBig4() {
        solveFor(listOf(1,2,3), 30000000, 261214)
    }

    @Test
    internal fun testSolveForBig5() {
        solveFor(listOf(2,3,1),30000000, 6895259)
    }

    @Test
    internal fun testSolveForBig6() {
        solveFor(listOf(3,2,1),30000000, 18)
    }

    @Test
    internal fun testSolveForBig7() {
        solveFor(listOf(3,1,2), 30000000,362)
    }
}