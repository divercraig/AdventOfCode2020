package day16

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TicketTranslatorTest {

    @Test
    internal fun testSumOfInvalidFields() {
        val translator = TicketTranslator(fileName = "src/test/resources/day16/test_input.txt")
        assertEquals(71, translator.sumOfInvalidFields(), "Sum of invalid fields is incorrect")
    }
}