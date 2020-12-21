@file:JvmName("Puzzle2")

package day21

fun main() {
    val ingredients = Ingredients(fileName = "src/main/resources/day21/input.txt")
    ingredients.identifyAllergenSources()
    val canonicalDangerousIngredients = ingredients.canonicalDangerousIngredientsList()
    println("Below is the canonical dangerous ingredients list:")
    println(canonicalDangerousIngredients)
}