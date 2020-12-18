package day18

import day18.advancedNewMath.evaluateWithAdvancedNewMath
import day18.newMath.evaluateWithNewMath
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class NewMathTest {

    private fun runExpressionWithNewMath(expression: String, expected: Int) {
        assertEquals(expected.toBigInteger(), evaluateWithNewMath(expression), "$expression didn't return the expected value of $expected with NEW MATH")
    }

    private fun runExpressionWithAdvancedNewMath(expression: String, expected: Int) {
        assertEquals(expected.toBigInteger(), evaluateWithAdvancedNewMath(expression), "$expression didn't return the expected value of $expected with ADVANCED NEW MATH")
    }


    @Test
    internal fun testNewMath1() {
        runExpressionWithNewMath("1 + 2 * 3 + 4 * 5 + 6", 71)
    }

    @Test
    internal fun testNewMath2() {
        runExpressionWithNewMath("1 + (2 * 3) + (4 * (5 + 6))", 51)
    }

    @Test
    internal fun testNewMath3() {
        runExpressionWithNewMath("2 * 3 + (4 * 5)", 26)
    }

    @Test
    internal fun testNewMath4() {
        runExpressionWithNewMath("5 + (8 * 3 + 9 + 3 * 4 * 3)", 437)
    }

    @Test
    internal fun testNewMath5() {
        runExpressionWithNewMath("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))", 12240)
    }

    @Test
    internal fun testNewMath6() {
        runExpressionWithNewMath("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2", 13632)
    }

    @Test
    internal fun testAdvancedNewMath1() {
        runExpressionWithAdvancedNewMath("1 + 2 * 3 + 4 * 5 + 6", 231)
    }

    @Test
    internal fun testAdvancedNewMath2() {
        runExpressionWithAdvancedNewMath("1 + (2 * 3) + (4 * (5 + 6))", 51)
    }

    @Test
    internal fun testAdvancedNewMath3() {
        runExpressionWithAdvancedNewMath("2 * 3 + (4 * 5)", 46)
    }

    @Test
    internal fun testAdvancedNewMath4() {
        runExpressionWithAdvancedNewMath("5 + (8 * 3 + 9 + 3 * 4 * 3)", 1445)
    }

    @Test
    internal fun testAdvancedNewMath5() {
        runExpressionWithAdvancedNewMath("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))", 669060)
    }

    @Test
    internal fun testAdvancedNewMath6() {
        runExpressionWithAdvancedNewMath("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2", 23340)
    }
}