package day1

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class ExpenseReportTest {

    @Test
    internal fun testFindSpecificProduct() {
        val expectedSpecificProduct = 514579
        val expenseReport = ExpenseReport("src/test/resources/day1/puzzle1/test_input1.txt")
        var specificProduct = expenseReport.findProductOf2TargetValues()
        assertEquals(expectedSpecificProduct, specificProduct, "specific product did not match expected result")
    }

    @Test
    internal fun testFindSpecificProductOfThree() {
        val expectedSpecificProduct = 241861950
        val expenseReport = ExpenseReport("src/test/resources/day1/puzzle1/test_input1.txt")
        var specificProduct = expenseReport.findProductOf3TargetValues()
        assertEquals(expectedSpecificProduct, specificProduct, "specific product of three did not match expected result")
    }
}