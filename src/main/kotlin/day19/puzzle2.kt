@file:JvmName("Puzzle2")

package day19

fun main() {
    var scanner = MessageScanner(fileName = "src/main/resources/day19/input.txt")
    println("Starting rule processing...")
    scanner.processRules(newRules = true)
    println("Completed Rule processing")
    println("Found ${scanner.matchWithRegex()} matches in the messages")
}