package day17

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ConwayCubeTest {

    @Test
    internal fun testCube() {
        val cube = ConwayCube(fileName = "src/test/resources/day17/test_input.txt")
        assertEquals(112, cube.bootCube(), "Unexpected Boot results")
    }
}