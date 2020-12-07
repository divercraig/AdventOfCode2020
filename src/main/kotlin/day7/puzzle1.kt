@file:JvmName("Puzzle1")

package day7

fun main() {

    val bagRules = BagRules("src/main/resources/day7/input.txt")

    println("Shiny Gold bags can be contained in ${bagRules.howManyBagColoursCanContain("shiny gold")} different coloured bags")
}