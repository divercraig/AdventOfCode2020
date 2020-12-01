package day1

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class ExpenseReportTest {

    @Test
    internal fun testFindSpecificProduct() {
        val expectedSpecificProduct = 514579
        val expenseReport = ExpenseReport("src/test/resources/day1/puzzle1/test_input1.txt")
        var specificProduct = expenseReport.findSpecificProduct()
        assertEquals(expectedSpecificProduct, specificProduct, "specific product did not match expected result")
    }
}