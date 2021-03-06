package day2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PasswordCheckTest {

    @Test
    internal fun testConstructor() {
        val line = "1-3 b: cdefg"
        val passwordCheck = PasswordCheck(input = line)
        assertEquals("cdefg", passwordCheck.password, "Password is not as expected")
        assertEquals(1, passwordCheck.ruleCharRange.first, "Rule Minimum Number is not as expected")
        assertEquals(3, passwordCheck.ruleCharRange.last, "Rule Maximum Number is not as expected")
        assertEquals('b', passwordCheck.ruleChar, "Rule Character is not as expected")
    }

    @Test
    fun testValidPassword() {
        val line = "1-3 a: abcde"
        val passwordCheck = PasswordCheck(input = line)
        assertTrue(passwordCheck.passwordIsValid(), "Password should be valid")
    }

    @Test
    fun testSecondValidPassword() {
        val line = "2-9 c: ccccccccc"
        val passwordCheck = PasswordCheck(input = line)
        assertTrue(passwordCheck.passwordIsValid(), "Password should be valid")
    }

    @Test
    fun testInvalidPassword() {
        val line = "1-3 b: cdefg"
        val passwordCheck = PasswordCheck(input = line)
        assertFalse(passwordCheck.passwordIsValid(), "Password should be invalid")
    }

    @Test
    fun testSinglePositionIsValid() {
        val line = "1-3 a: abcde"
        val passwordCheck = PasswordCheck(input = line)
        assertTrue(passwordCheck.passwordPositionsAreValid(), "Password position should be valid because a appears in position 1 and not 3")
    }

    @Test
    fun testNoPositionIsInvalid() {
        val line = "1-3 b: cdefg"
        val passwordCheck = PasswordCheck(input = line)
        assertFalse(passwordCheck.passwordPositionsAreValid(), "Password position should be invalid because b appears in neither position")
    }

    @Test
    fun testTwoPositionsIsInvalid(){
        val line = "2-9 c: ccccccccc"
        val passwordCheck = PasswordCheck(input = line)
        assertFalse(passwordCheck.passwordPositionsAreValid(), "Password position should be invalid because c appears in position 2 and 9")
    }

}