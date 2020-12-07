package day6

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PlaneCustomsFormReaderTest {

    @Test
    internal fun testSumOfYesAnswer() {
        val planeForm = PlaneCustomsFormReader("src/test/resources/day6/test_input1.txt")
        assertEquals(11, planeForm.sumOfYesAnswers, "Sum of Yes answers not as expected")
    }

    @Test
    internal fun testSumOfGroupCommonYesAnswers() {
        val planeForm = PlaneCustomsFormReader("src/test/resources/day6/test_input1.txt")
        assertEquals(6, planeForm.sumOfGroupCommonYesAnswers, "Sum Of Group Common Yes Answers not as expected")
    }
}