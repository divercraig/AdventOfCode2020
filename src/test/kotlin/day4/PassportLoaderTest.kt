package day4

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PassportLoaderTest {

    @Test
    internal fun testCountPassportWithRequiredFields() {
        val passportLoader = PassportLoader("src/test/resources/day4/test_input1.txt")
        assertEquals(2, passportLoader.countPassportsWithRequiredFields(), "The number of passports with valid fields is incorrect")
    }

    @Test
    internal fun testCountValidPassport() {
        val passportLoader = PassportLoader("src/test/resources/day4/test_passports.txt")
        assertEquals(4, passportLoader.countValidPassports(), "The number of valid passports is incorrect")
    }
}