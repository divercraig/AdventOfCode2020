package day25

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test

class DoorEncryptionTest {

    @Test
    internal fun testFindLoops() {
        val encryption = DoorEncryption(cardPubKey=5764801L, doorPubKey=17807724L)
        assertFalse(encryption.attackDoor, "Should be attacking the card")
        assertEquals(8, encryption.loopSize, "Loop value is wrong")
    }

    @Test
    internal fun testCalcEncryptionKey() {
        val encryption = DoorEncryption(cardPubKey=5764801L, doorPubKey=17807724L)
        assertEquals(14897079, encryption.calcEncryptionKey(), "Encryption Key is wrong")
    }
}