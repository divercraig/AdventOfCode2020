@file:JvmName("Puzzle1")

package day3

fun main() {
    val treeMap = TreeMap("src/main/resources/day3/puzzle1/input.txt")

    try {
        println("Encountered ${treeMap.howManyTreesOnPath(3,1)} trees on a path of 3 right and 1 down")
    } catch (e: Exception) {
        println("Exception occured: ${e.message}")
    }
}