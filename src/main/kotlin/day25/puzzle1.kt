@file:JvmName("Puzzle1")

package day25

fun main() {
    val cardPubKey = 10441485L
    val doorPubKey = 1004920L

    val encryption = DoorEncryption(cardPubKey=cardPubKey, doorPubKey = doorPubKey)
    println("Attacking Door: ${encryption.attackDoor}")
    println("Attacking Card: ${!encryption.attackDoor}")
    println("Calculated Loops: ${encryption.loopSize}")
    println("Encryption Key: ${encryption.calcEncryptionKey()}")
}