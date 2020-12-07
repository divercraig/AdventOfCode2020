@file:JvmName("Puzzle2")

package day7

fun main() {

    val bagRules = BagRules("src/main/resources/day7/puzzle1/input.txt")

    println("Shiny Gold bags require ${bagRules.howManyBagsMustContain("shiny gold")} bags")
}