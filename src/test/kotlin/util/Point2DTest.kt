package util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Point2DTest {

    @Test
    internal fun testGetters() {
        val point = Point2D(4,9)
        assertEquals(4, point.x, "X Get not working as expected")
        assertEquals(9, point.y, "Y Getter not working as expected")
    }

    @Test
    internal fun testEquality() {
        val point = Point2D(3,8)
        val point2 = Point2D(x=3, y=8)
        assertEquals(point, point2, "Equality not working as expected")
    }
}