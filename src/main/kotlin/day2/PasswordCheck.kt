package day2

class PasswordCheck(input: String) {
    val ruleChar: Char
    val ruleCharRange: IntRange
    val password: String

    init {
        val inputParts = input.split(":")
        password = inputParts[1].trim()
        val rule = inputParts[0].trim()

        val ruleParts = rule.split(" ")
        ruleChar = ruleParts[1][0] // Take First Character (There should only be 1)
        val ruleRange = ruleParts[0]

        val rangeParts = ruleRange.split("-")
        ruleCharRange = IntRange(rangeParts[0].toInt(), rangeParts[1].toInt())
    }

    fun passwordIsValid(): Boolean {
        val matchingChars = password.filter { it == ruleChar }
        return ruleCharRange.contains(matchingChars.length)
    }
}