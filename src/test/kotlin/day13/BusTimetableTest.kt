package day13

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BusTimetableTest {

    @Test
    internal fun testEarliestDeparture() {
        val timetable = BusTimetable(fileName = "src/test/resources/day13/test_input1.txt")
        assertEquals(Pair(59, 5), timetable.earliestDeparture(), "Earliest departure is not as expected, should be bus 59 with a 5 minute wait")
    }

    @Test
    internal fun testCompetitionTime() {
        val timetable = BusTimetable(fileName = "src/test/resources/day13/test_input1.txt")
        assertEquals(1068781L, timetable.competitionTime(), "Competition time is not as expected")
    }

    @Test
    internal fun testCompetitionTime2() {
        val timetable = BusTimetable(fileName = "src/test/resources/day13/test_input2.txt")
        assertEquals(3417L, timetable.competitionTime(), "Competition time is not as expected")
    }

    @Test
    internal fun testCompetitionTime3() {
        val timetable = BusTimetable(fileName = "src/test/resources/day13/test_input3.txt")
        assertEquals(754018L, timetable.competitionTime(), "Competition time is not as expected")
    }

    @Test
    internal fun testCompetitionTime4() {
        val timetable = BusTimetable(fileName = "src/test/resources/day13/test_input4.txt")
        assertEquals(779210L, timetable.competitionTime(), "Competition time is not as expected")
    }

    @Test
    internal fun testCompetitionTime5() {
        val timetable = BusTimetable(fileName = "src/test/resources/day13/test_input5.txt")
        assertEquals(1261476L, timetable.competitionTime(), "Competition time is not as expected")
    }

    @Test
    internal fun testCompetitionTime6() {
        val timetable = BusTimetable(fileName = "src/test/resources/day13/test_input6.txt")
        assertEquals(1202161486L, timetable.competitionTime(), "Competition time is not as expected")
    }
}