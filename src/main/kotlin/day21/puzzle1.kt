@file:JvmName("Puzzle1")

package day21

fun main() {
    val ingredients = Ingredients(fileName = "src/main/resources/day21/input.txt")
    ingredients.identifyAllergenSources()
    val nonAllergicIngredientOccurrences = ingredients.occurrencesOfNonAllergicIngredients()
    println("There are $nonAllergicIngredientOccurrences occurrences of non allergic ingredients")
}