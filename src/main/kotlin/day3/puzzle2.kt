@file:JvmName("Puzzle2")

package day3

fun main() {
    val treeMap = TreeMap("src/main/resources/day3/input.txt")

    // We're using BigInteger because we're multiplying 5 large numbers together.
    // This will help avoid Integer Overflow
    var multipleOfTreesEncountered = treeMap.howManyTreesOnPath(1,1).toBigInteger()
    multipleOfTreesEncountered *= treeMap.howManyTreesOnPath(3,1).toBigInteger()
    multipleOfTreesEncountered *= treeMap.howManyTreesOnPath(5,1).toBigInteger()
    multipleOfTreesEncountered *= treeMap.howManyTreesOnPath(7,1).toBigInteger()
    multipleOfTreesEncountered *= treeMap.howManyTreesOnPath(1,2).toBigInteger()

    println("Total number of trees encountered on 5 proposed runs is $multipleOfTreesEncountered")
}