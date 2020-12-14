package day14

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@ExperimentalStdlibApi
class DockingComputerTest {

    @Test
    internal fun testReadInstructions() {
        val comp = DockingComputer(fileName = "src/test/resources/day14/test_input1.txt")
        assertEquals(4, comp.instructions.size, "Incorrect number of instructions loaded")
    }

    @Test
    internal fun testApplyMask() {
        val comp = DockingComputer(fileName = "src/test/resources/day14/test_input1.txt")
        val result = comp.applyMask(11L, "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X")
        assertEquals(73L, result, "mask application incorrect")
    }

    @Test
    internal fun testApplyMask2() {
        val comp = DockingComputer(fileName = "src/test/resources/day14/test_input1.txt")
        val result = comp.applyMask(101L, "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X")
        assertEquals(101L, result, "mask application incorrect")
    }

    @Test
    internal fun testApplyMask3() {
        val comp = DockingComputer(fileName = "src/test/resources/day14/test_input1.txt")
        val result = comp.applyMask(0L, "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X")
        assertEquals(64L, result, "mask application incorrect")
    }

    @Test
    internal fun testRunProgram() {
        val comp = DockingComputer(fileName = "src/test/resources/day14/test_input1.txt")
        assertEquals(165L, comp.runProgram(), "Program result not as expected" )
    }

    @Test
    internal fun testRunProgramV2() {
        val comp = DockingComputer(fileName = "src/test/resources/day14/test_input2.txt")
        assertEquals(208L, comp.runProgramV2(), "Program v2 results not as expected")
    }

}