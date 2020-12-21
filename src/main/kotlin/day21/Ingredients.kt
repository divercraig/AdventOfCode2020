package day21

import java.io.File

class Ingredients(fileName: String) {
    val foods: List<Pair<Set<String>, Set<String>>>
    val ingredients: Set<String>
    val allergens: Set<String>
    var allergenSources = mutableMapOf<String, MutableSet<String>>()

    init {
        val foodList = mutableListOf<Pair<Set<String>, Set<String>>>()
        val ingredientsSet = mutableSetOf<String>()
        val allergensSet = mutableSetOf<String>()
        File(fileName).forEachLine {
            val parts = it.split(" (")
            val ingredientsString = parts[0]
            val allergensString = parts[1].removeSuffix(")").removePrefix("contains ")

            val ingredients = mutableSetOf<String>()
            for(ingredient in ingredientsString.split(" "))
            {
                ingredients.add(ingredient)
                ingredientsSet.add(ingredient)
            }

            val allergens = mutableSetOf<String>()
            for(allergen in allergensString.split(", ")) {
                allergens.add(allergen)
                allergensSet.add(allergen)
            }
            foodList.add(Pair(ingredients, allergens))
        }
        foods = foodList
        ingredients = ingredientsSet
        allergens = allergensSet
    }

    fun identifyAllergenSources() {
        for(allergen in allergens) {
            for(food in foods) {
                if(food.second.contains(allergen)) {
                    if(allergenSources.keys.contains(allergen)) {
                        allergenSources[allergen] = allergenSources[allergen]!!.intersect(food.first).toMutableSet()
                    } else {
                        allergenSources[allergen] = food.first.toMutableSet()
                    }
                }
            }
        }

        var allergenIdentified = mutableSetOf<String>()
        while(allergenSources.values.fold(1, {acc, next -> maxOf(acc, next.size)}) > 1) {
            for(entry in allergenSources) {
                if(entry.value.size == 1) {
                    allergenIdentified.add(entry.value.first())
                }
            }

            for(entry in allergenSources) {
                if(entry.value.size > 1) {
                    entry.value.removeAll(allergenIdentified)
                }
            }

        }
    }

    fun occurrencesOfNonAllergicIngredients() : Long {
        var sum = 0L
        val allergicIngredients = mutableSetOf<String>()
        for(allergenSource in allergenSources.values) {
            for(ingredient in allergenSource) {
                allergicIngredients.add(ingredient)
            }
        }

        for(ingredientAllergen in foods) {
            val ingredients = ingredientAllergen.first
            for(ingredient in ingredients) {
                if(!allergicIngredients.contains(ingredient)) {
                    sum++
                }
            }
        }

        return sum
    }

    fun canonicalDangerousIngredientsList() : String {
        var dangerousIngredients = ""
        var listOfAllergen = mutableListOf<String>()
        for(allergen in allergenSources.keys) {
            listOfAllergen.add(allergen)
        }

        listOfAllergen.sort()

        for(allergen in listOfAllergen) {
            if(dangerousIngredients.isNotEmpty()) {
                dangerousIngredients += ","
            }
            dangerousIngredients += allergenSources[allergen].first()
        }

        return dangerousIngredients
    }

}