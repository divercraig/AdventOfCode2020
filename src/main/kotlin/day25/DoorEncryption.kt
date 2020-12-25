package day25

class DoorEncryption(val cardPubKey: Long, val doorPubKey: Long) {
    val attackDoor: Boolean
    val loopSize: Int

    init {
        if(doorPubKey < cardPubKey) {
            attackDoor = true
            loopSize = calcLoopSize(doorPubKey)
        } else {
            attackDoor = false
            loopSize = calcLoopSize(cardPubKey)
        }

    }

    private fun transformSubjectNumber(subject: Long, loopSize: Int) : Long {
        var value = 1L

        repeat(loopSize) {
            value *= subject
            value %= 20201227
        }

        return value
    }

    private fun calcLoopSize(pubKey: Long): Int {
        var loop = 1
        var value = 1L

        while(true) {
            value *= 7
            value %= 20201227
            if(value == pubKey) {
                return loop
            }
            loop++
        }
    }

    fun calcEncryptionKey(): Long {
        return if(attackDoor) {
            transformSubjectNumber(cardPubKey, loopSize)
        } else {
            transformSubjectNumber(doorPubKey, loopSize)
        }

    }




}