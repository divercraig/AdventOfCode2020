package day4

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PassportLoaderTest {

    @Test
    internal fun testCountValidPassport() {
        val passportLoader = PassportLoader("src/test/resources/day4/puzzle1/test_input1.txt")
        assertEquals(2, passportLoader.countValidPassports(), "The number of valid passports is incorrect")
    }
}