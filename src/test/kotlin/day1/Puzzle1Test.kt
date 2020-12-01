package day1

import org.junit.jupiter.api.Test
import day1.findSpecificProduct
import day1.readExpenseReport
import org.junit.jupiter.api.Assertions.assertEquals

class Puzzle1Test {

    @Test
    internal fun testFindSpecificProduct() {
        val expectedSpecificProduct = 514579
        var testExpenseReport = readExpenseReport("src/test/resources/day1/puzzle1/test_input1.txt")
        var specificProduct = findSpecificProduct(testExpenseReport)
        assertEquals(expectedSpecificProduct, specificProduct, "specific product did not match expected result")
    }
}