package day6

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CustomsFormTest {

    @Test
    internal fun testNumberOfDifferedYesAnswers() {
        val strings = listOf("abcx", "abcy", "abcz")
        val form = CustomsForm(strings)
        assertEquals(6, form.numberOfDifferedYesAnswers, "Number of Yes answers is not as expected")
    }

    @Test
    internal fun testNumberOfQuestionsEveryoneSaidYesTo() {
        val strings = listOf("abcx", "abcy", "abcz")
        val form = CustomsForm(strings)
        assertEquals(3, form.numberOfQuestionsEveryoneSaidYesTo, "Number of questions everyone said yes to is not as expected")
    }
}