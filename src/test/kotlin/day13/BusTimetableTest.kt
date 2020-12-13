package day13

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BusTimetableTest {

    @Test
    internal fun testEarliestDeparture() {
        val timetable = BusTimetable(fileName = "src/test/resources/day13/test_input1.txt")
        assertEquals(Pair(59, 5), timetable.earliestDeparture(), "Earliest departure is not as expected, should be bus 59 with a 5 minute wait")
    }
}