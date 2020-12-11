package day11

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SeatMapTest {

    @Test
    internal fun testSeatsOccupiedAfterChaos() {
        val seatMap = SeatMap("src/test/resources/day11/test_input1.txt")
        assertEquals(37, seatMap.seatsOccupiedAfterChaos(), "Doesn't have expected number of occupied seats")
    }
}