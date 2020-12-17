package day17

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ConwayHyperCubeTest {

    @Test
    internal fun testCube() {
        val cube = ConwayHyperCube(fileName = "src/test/resources/day17/test_input.txt")
        assertEquals(848, cube.bootCube(), "Unexpected Boot results")
    }

    @Test
    internal fun testCubeForOnePhase() {
        val cube = ConwayHyperCube(fileName = "src/test/resources/day17/test_input.txt")
        assertEquals(29, cube.bootCube(1), "Unexpected Boot results")
    }
}