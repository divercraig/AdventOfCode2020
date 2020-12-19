package day19

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MessageScannerTest {

    @Test
    internal fun testConstructor() {
        val scanner = MessageScanner(fileName = "src/test/resources/day19/test_input1.txt")
        assertEquals(6, scanner.unprocessedRules.size, "Scanner doesn't have all the expected rules")
        assertEquals(5, scanner.messages.size, "Scanner doesn't have all the expected messages")
        assertEquals(0, scanner.processedRules.size, "Rules should still not be processed")
    }

    @Test
    internal fun testProcessRules() {
        val scanner = MessageScanner(fileName = "src/test/resources/day19/test_input1.txt")
        scanner.processRules()
        assertEquals(scanner.unprocessedRules.size, scanner.unprocessedRules.size, "Scanner should have a processed rule for each unprocessed rule")
        assertEquals(6, scanner.processedRules.size, "Scanner should have 6 processed rules")
    }

    @Test
    internal fun testHowManyMessagesMatch() {
        val scanner = MessageScanner(fileName = "src/test/resources/day19/test_input1.txt")
        scanner.processRules()
        assertEquals(2, scanner.matchWithRegex(), "Scanner should have 2 matching messages")
    }

    @Test
    internal fun testNewRules() {
        val scanner = MessageScanner(fileName = "src/test/resources/day19/test_input2.txt")
        scanner.processRules(newRules=true)
        assertEquals(12, scanner.matchWithRegex(), "scanner should have 12 matching messages")
    }
}