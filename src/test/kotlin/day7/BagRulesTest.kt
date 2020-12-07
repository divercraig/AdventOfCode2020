package day7

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BagRulesTest {

    @Test
    internal fun testConstructor() {
        val bagRules = BagRules("src/test/resources/day7/test_input1.txt")
        assertEquals(7, bagRules.canBeContainedIn.size, "Only 7 bags can go in other bags")
    }

    @Test
    internal fun testHowManyBagColoursCanContain() {
        val bagRules = BagRules("src/test/resources/day7/test_input1.txt")
        assertEquals(4, bagRules.howManyBagColoursCanContain("shiny gold"), "4 colours of bags can contain shiny gold")
    }

    @Test
    internal fun testHowManyBagsMustContain() {
        var bagRules = BagRules("src/test/resources/day7/test_input1.txt")
        assertEquals(32, bagRules.howManyBagsMustContain("shiny gold"), "32 bags required in a shiny gold bag")
    }

    @Test
    internal fun testHowManyBagsMustContain2() {
        var bagRules = BagRules("src/test/resources/day7/test_input2.txt")
        assertEquals(126, bagRules.howManyBagsMustContain("shiny gold"), "126 bags required in a shiny gold bag")
    }
}