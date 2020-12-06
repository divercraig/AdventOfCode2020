package day6

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CustomsFormTest {

    @Test
    internal fun testConstructor() {
        val strings = listOf("abcx", "abcy", "abcz")
        val form = CustomsForm(strings)
        assertEquals(6, form.numberOfDifferedYesAnswers, "Number of Yes answers is not as expected")
    }
}