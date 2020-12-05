package day5

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SeatTest {

    private fun testSeat(input:String, expectedRow:Int, expectedColumn:Int, expectedId: Int) {
        val seat = Seat(input)
        assertEquals(expectedRow, seat.row, "Row not as expected")
        assertEquals(expectedColumn, seat.column, "Column not as expected")
        assertEquals(expectedId, seat.seatId, "Seat ID is not as expected")
    }

    @Test
    internal fun testSeatConstructor1() {
        testSeat("FBFBBFFRLR", 44, 5, 357)
    }

    @Test
    internal fun testSeatConstructor2() {
        testSeat("BFFFBBFRRR", 70, 7, 567)
    }

    @Test
    internal fun testSeatConstructor3() {
        testSeat("FFFBBBFRRR", 14, 7, 119)
    }

    @Test
    internal fun testSeatConstructor4() {
        testSeat("BBFFBBFRLL", 102, 4, 820)
    }

    @Test
    internal fun testSeatConstructor5() {
        testSeat("FFFFBBBLLL", 7, 0, 56)
    }

    @Test
    internal fun testSeatConstructor6() {
        testSeat("FFFFBBFLLL", 6, 0, 48 )
    }
}