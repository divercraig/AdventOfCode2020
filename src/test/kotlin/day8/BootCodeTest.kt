package day8

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BootCodeTest {

    @Test
    internal fun testRunUntilLoop() {
        val bootCode = BootCode("src/test/resources/day8/test_input1.txt")
        val accumulator = bootCode.runUntilLoop()
        assertEquals(5, accumulator, "Accumulator should have the value 5 before the first loop")
    }

    @Test
    internal fun testRunSelfHealingProgram() {
        val bootCode = BootCode("src/test/resources/day8/test_input1.txt")
        val accumulator = bootCode.runSelfHealingProgram()
        assertEquals(8, accumulator, "Accumulator should have the value 8 after it executes")
    }
}