package day18

import day18.newMath.evaluateWithNewMath
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class NewMathTest {

    private fun runExpression(expression: String, expected: Int) {
        assertEquals(expected.toBigInteger(), evaluateWithNewMath(expression), "$expression didn't return the expected value of $expected")
    }


    @Test
    internal fun testExpression1() {
        runExpression("1 + 2 * 3 + 4 * 5 + 6", 71)
    }

    @Test
    internal fun testExpression2() {
        runExpression("1 + (2 * 3) + (4 * (5 + 6))", 51)
    }

    @Test
    internal fun testExpression3() {
        runExpression("2 * 3 + (4 * 5)", 26)
    }

    @Test
    internal fun testExpression4() {
        runExpression("5 + (8 * 3 + 9 + 3 * 4 * 3)", 437)
    }

    @Test
    internal fun testExpression5() {
        runExpression("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))", 12240)
    }

    @Test
    internal fun testExpression6() {
        runExpression("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2", 13632)
    }
}