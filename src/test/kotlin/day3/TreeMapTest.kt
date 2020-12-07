package day3

import day1.ExpenseReport
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TreeMapTest {

    internal fun testHowManyTreesOnPath(x:Int, y:Int, expected:Int) {
        val map = TreeMap("src/test/resources/day3/test_input1.txt")
        var treesEncountered = map.howManyTreesOnPath(x,y)
        Assertions.assertEquals(expected, treesEncountered, "Number of tree's encountered on path R${x}, D${y} was not as expected")
    }

    @Test
    internal fun testHowManyTreesOnPathR3D1() {
        testHowManyTreesOnPath(3,1,7)
    }

    @Test
    internal fun testHowManyTreesOnPathR1D1() {
        testHowManyTreesOnPath(1,1,2)
    }

    @Test
    internal fun testHowManyTreesOnPathR5D1() {
        testHowManyTreesOnPath(5,1,3)
    }

    @Test
    internal fun testHowManyTreesOnPathR7D1() {
        testHowManyTreesOnPath(7,1,4)
    }

    @Test
    internal fun testHowManyTreesOnPathR1D2() {
        testHowManyTreesOnPath(1,2,2)
    }
}