package day7

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BagRulesTest {

    @Test
    internal fun testConstructor() {
        val bagRules = BagRules("src/test/resources/day7/puzzle1/input.txt")
        assertEquals(7, bagRules.canBeContainedIn.size, "Only 7 bags can go in other bags")
    }

    @Test
    internal fun testHowManyBagColoursCanContain() {
        val bagRules = BagRules("src/test/resources/day7/puzzle1/input.txt")
        assertEquals(4, bagRules.howManyBagColoursCanContain("shiny gold"), "4 colours of bags can contain shiny gold")
    }
}