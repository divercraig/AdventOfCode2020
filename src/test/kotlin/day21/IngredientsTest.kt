package day21

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class IngredientsTest {

    @Test
    internal fun testFoods() {
        var ingredients = Ingredients(fileName = "src/test/resources/day21/test_input1.txt")
        assertEquals(4, ingredients.foods.size, "Should have 4 foods")

        assertEquals(4, ingredients.foods[0].first.size, "First item should have 4 ingredients")
        assertEquals(2, ingredients.foods[0].second.size, "First item should have 2 allergens")

        assertEquals(4, ingredients.foods[1].first.size, "Second item should have 4 ingredients")
        assertEquals(1, ingredients.foods[1].second.size, "Second item should have 1 allergen")

        assertEquals(2, ingredients.foods[2].first.size, "Third item should have 2 ingredients")
        assertEquals(1, ingredients.foods[2].second.size, "Third item should have 1 allergen")

        assertEquals(3, ingredients.foods[3].first.size, "Fourth item should have 3 ingredients")
        assertEquals(1, ingredients.foods[3].second.size, "Fourth item should have 1 allergen")

        assertEquals(7, ingredients.ingredients.size, "Should have 8 ingredients in total")
        assertEquals(3, ingredients.allergens.size, "Should have 3 ingredients in total")
    }

    @Test
    internal fun testIdentifyAllergenSources() {
        var ingredients = Ingredients(fileName = "src/test/resources/day21/test_input1.txt")
        ingredients.identifyAllergenSources()
        assertEquals("mxmxvkd", ingredients.allergenSources["dairy"]!!.first(), "Dairy source has been identified incorrectly")
        assertEquals("sqjhc", ingredients.allergenSources["fish"]!!.first(), "Fish source has been identified incorrectly")
        assertEquals("fvjkl", ingredients.allergenSources["soy"]!!.first(), "Soy source has been identified incorrectly")

    }

    @Test
    internal fun testOccurrencesOfNonAllergicIngredients() {
        var ingredients = Ingredients(fileName = "src/test/resources/day21/test_input1.txt")
        ingredients.identifyAllergenSources()
        assertEquals(5, ingredients.occurrencesOfNonAllergicIngredients(), "There should be 5 occurrences of non allergic ingredients")
    }
}