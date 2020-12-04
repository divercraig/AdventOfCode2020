package day4

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class PassportTest {

    private fun testValidBirthYear(passport: String) {
        val passport = Passport(passport)
        assertTrue(passport.hasValidBirthYear(), "${passport} should contain a valid birth year")
    }

    private fun testInvalidBirthYear(passport: String) {
        val passport = Passport(passport)
        assertFalse(passport.hasValidBirthYear(), "${passport} should contain an invalid birth year")
    }

    @Test
    internal fun testHasInvalidBirthYearWithNonInt() {
        testInvalidBirthYear("byr:abdc")
    }

    @Test
    internal fun testHasValidBirthYear() {
        testValidBirthYear("byr:1940")
    }

    @Test
    internal fun testHasValidBirthYearOnLowValue() {
        testValidBirthYear("byr:1920")
    }

    @Test
    internal fun testHasValidBirthYearOnHighValue() {
        testValidBirthYear("byr:2002")
    }

    @Test
    internal fun testHasinValidBirthYearOnLowValue() {
        testInvalidBirthYear("byr:1919")
    }

    @Test
    internal fun testHasinValidBirthYearOnHighValue() {
        testInvalidBirthYear("byr:2003")
    }

    private fun testValidIssueYear(passport: String) {
        val passport = Passport(passport)
        assertTrue(passport.hasValidIssueYear(), "${passport} should contain a valid issue year")
    }

    private fun testInvalidIssueYear(passport: String) {
        val passport = Passport(passport)
        assertFalse(passport.hasValidIssueYear(), "${passport} should contain an invalid issue year")
    }

    @Test
    internal fun testHasInvalidIssueYearWithNonInt() {
        testInvalidIssueYear("iyr:abdc")
    }

    @Test
    internal fun testHasValidIssueYear() {
        testValidIssueYear("iyr:2015")
    }

    @Test
    internal fun testHasValidIssueYearOnLowValue() {
        testValidIssueYear("iyr:2010")
    }

    @Test
    internal fun testHasValidIssueYearOnHighValue() {
        testValidIssueYear("iyr:2020")
    }

    @Test
    internal fun testHasInvalidIssueYearOnLowValue() {
        testInvalidIssueYear("iyr:2009")
    }

    @Test
    internal fun testHasInvalidIssueYearOnHighValue() {
        testInvalidIssueYear("iyr:2021")
    }

    private fun testValidExpiryYear(passport: String) {
        val passport = Passport(passport)
        assertTrue(passport.hasValidExpirationYear(), "${passport} should contain a valid expiration year")
    }

    private fun testInvalidExpiryYear(passport: String) {
        val passport = Passport(passport)
        assertFalse(passport.hasValidExpirationYear(), "${passport} should contain an invalid expiration year")
    }

    @Test
    internal fun testHasInvalidExpiryYearWithNonInt() {
        testInvalidExpiryYear("eyr:abdc")
    }

    @Test
    internal fun testHasValidExpiryYear() {
        testValidExpiryYear("eyr:2025")
    }

    @Test
    internal fun testHasValidExpiryYearOnLowValue() {
        testValidExpiryYear("eyr:2020")
    }

    @Test
    internal fun testHasValidExpiryYearOnHighValue() {
        testValidExpiryYear("eyr:2030")
    }

    @Test
    internal fun testHasInvalidExpiryYearOnLowValue() {
        testInvalidExpiryYear("eyr:2019")
    }

    @Test
    internal fun testHasInvalidExpiryYearOnHighValue() {
        testInvalidExpiryYear("eyr:2031")
    }

    @Test
    internal fun testHasValidHeightIncorrectMeasurement() {
        val passport = Passport("hgt:123ft")
        assertFalse(passport.hasValidHeight(), "${passport} should contain a invalid height measurement")
    }

    @Test
    internal fun testHasValidHeightNoMeasurement() {
        val passport = Passport("hgt:123")
        assertFalse(passport.hasValidHeight(), "${passport} should contain a invalid height measurement")
    }

    @Test
    internal fun testHasValidHeightTooLowInches() {
        val passport = Passport("hgt:58in")
        assertFalse(passport.hasValidHeight(), "${passport} should contain a invalid height")
    }

    @Test
    internal fun testHasValidHeightTooLowCM() {
        val passport = Passport("hgt:149cm")
        assertFalse(passport.hasValidHeight(), "${passport} should contain a invalid height")
    }

    @Test
    internal fun testHasValidHeightTooHighInches() {
        val passport = Passport("hgt:77in")
        assertFalse(passport.hasValidHeight(), "${passport} should contain a invalid height")
    }

    @Test
    internal fun testHasValidHeightTooHighCM() {
        val passport = Passport("hgt:194cm")
        assertFalse(passport.hasValidHeight(), "${passport} should contain a invalid height")
    }

    @Test
    internal fun testHasValidHeightLowInches() {
        val passport = Passport("hgt:59in")
        assertTrue(passport.hasValidHeight(), "${passport} should contain a valid height")
    }

    @Test
    internal fun testHasValidHeightLowCM() {
        val passport = Passport("hgt:193cm")
        assertTrue(passport.hasValidHeight(), "${passport} should contain a valid height")
    }

    @Test
    internal fun testHasValidHeightHighInches() {
        val passport = Passport("hgt:76in")
        assertTrue(passport.hasValidHeight(), "${passport} should contain a valid height")
    }

    @Test
    internal fun testHasValidHeightHighCM() {
        val passport = Passport("hgt:193cm")
        assertTrue(passport.hasValidHeight(), "${passport} should contain a valid height")
    }

    @Test
    internal fun testValidEyeColours() {
        val passport = Passport("ecl:grn")
        assertTrue(passport.hasValidEyeColour(), "${passport} should contain a valid eye colour")
    }

    @Test
    internal fun testInvalidEyeColours() {
        val passport = Passport("ecl:xxx")
        assertFalse(passport.hasValidEyeColour(), "${passport} should contain a invalid eye colour")
    }

    @Test
    internal fun testValidPassportId() {
        val passport = Passport("pid:012345678")
        assertTrue(passport.hasValidPassportId(), "${passport} should contain a valid passport ID")
    }

    @Test
    internal fun testValidPassportIdTooLong() {
        val passport = Passport("pid:0123456789")
        assertFalse(passport.hasValidPassportId(), "${passport} passport id should be too long")
    }

    @Test
    internal fun testValidPassportIdTooShort() {
        val passport = Passport("pid:12345678")
        assertFalse(passport.hasValidPassportId(), "${passport} passport id should be too short")
    }

    @Test
    internal fun testValidPassportIdCharacters() {
        val passport = Passport("pid:01234a678")
        assertFalse(passport.hasValidPassportId(), "${passport} passport id should be too short")
    }

    @Test
    internal fun testValidHairColour() {
        val passport = Passport("hcl:#0a5b9f")
        assertTrue(passport.hasValidHairColour(), "${passport} should have a valid hair color")
    }

    @Test
    internal fun testValidHairColourTooLong() {
        val passport = Passport("hcl:#0a5b9fa")
        assertFalse(passport.hasValidHairColour(), "${passport} hair colour is too long")
    }

    @Test
    internal fun testValidHairColourTooShort() {
        val passport = Passport("hcl:#0a5b9")
        assertFalse(passport.hasValidHairColour(), "${passport} hair colour is too short")
    }

    @Test
    internal fun testValidHairColourInvalidCharacters() {
        val passport = Passport("hcl:#0a5b9gg")
        assertFalse(passport.hasValidHairColour(), "${passport} hair colour has invalid characters")
    }

    @Test
    internal fun testValidHairColourNoHash() {
        val passport = Passport("hcl:012345")
        assertFalse(passport.hasValidHairColour(), "${passport} hair colour has doesn't have a hash")
    }
}