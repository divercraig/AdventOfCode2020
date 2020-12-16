package day16

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TicketTranslatorTest {

    @Test
    internal fun testSumOfInvalidFields() {
        val translator = TicketTranslator(fileName = "src/test/resources/day16/test_input1.txt")
        assertEquals(71, translator.sumOfInvalidFields(), "Sum of invalid fields is incorrect")
    }

    @Test
    internal fun testIdentifyColumns() {
        val translator = TicketTranslator(fileName = "src/test/resources/day16/test_input2.txt")
        val myMappedTicket = translator.identifyColumns()
        assertEquals(12, myMappedTicket["class"], "Class is not as expected")
        assertEquals(11, myMappedTicket["row"], "Row is not as expected")
        assertEquals(13, myMappedTicket["seat"], "Seat is not as expected")
    }
}