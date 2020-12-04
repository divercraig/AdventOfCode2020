package day4

class Passport(passportString: String) {
    private val data: Map<String, String>
    private val birthYearKey = "byr"
    private val issueYearKey = "iyr"
    private val expirationYearKey = "eyr"
    private val heightKey = "hgt"
    private val hairColorKey = "hcl"
    private val eyeColorKey = "ecl"
    private val passportIdKey = "pid"

    init {
        data = HashMap()
        val fields = passportString.split(' ')
        for(field in fields) {
            val parts = field.split(':')
            assert(parts.size == 2)
            data[parts[0]] = parts[1]
        }
    }

    fun hasRequiredFields(): Boolean {
        return data.containsKey(birthYearKey)
                && data.containsKey(issueYearKey)
                && data.containsKey(expirationYearKey)
                && data.containsKey(heightKey)
                && data.containsKey(hairColorKey)
                && data.containsKey(eyeColorKey)
                && data.containsKey(passportIdKey)
    }

    fun isValid(): Boolean {
        return hasRequiredFields()
                && hasValidBirthYear()
                && hasValidExpirationYear()
                && hasValidIssueYear()
                && hasValidHeight()
                && hasValidHairColour()
                && hasValidEyeColour()
                && hasValidPassportId()
    }

    fun hasValidBirthYear(): Boolean {
        val birthYear: Int
        try {
            birthYear = (data[birthYearKey] ?: error("null birth year")).toInt()
        } catch (e: Exception) {
            return false
        }
        return IntRange(1920, 2002).contains(birthYear)
    }

    fun hasValidIssueYear(): Boolean {
        val issueYear: Int
        try {
            issueYear = (data[issueYearKey] ?: error("null issue year")).toInt()
        } catch (e: Exception) {
            return false
        }
        return IntRange(2010, 2020).contains(issueYear)
    }

    fun hasValidExpirationYear(): Boolean {
        val expiryYear: Int
        try {
            expiryYear = (data[expirationYearKey] ?: error("null issue year")).toInt()
        } catch (e: Exception) {
            return false
        }
        return IntRange(2020, 2030).contains(expiryYear)
    }

    fun hasValidHeight(): Boolean {
        val height = data[heightKey]
        val measurement = height?.takeLast(2)
        val size = height?.takeWhile{it.isDigit()}?.toInt()
        val validMeasurements = listOf("cm", "in")
        if(!validMeasurements.contains(measurement)) return false

        if("cm" == measurement) {
            return IntRange(150, 193).contains(size)
        }

        // Must be inches
        return IntRange(59, 76).contains(size)

    }

    fun hasValidHairColour(): Boolean {
        val regex = Regex("^#[0-9a-f]{6}\$")
        return regex.containsMatchIn(data[hairColorKey] ?: error(""))
    }

    fun hasValidEyeColour(): Boolean {
        val validColours = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
        return validColours.contains(data[eyeColorKey])
    }

    fun hasValidPassportId(): Boolean {
        val regex = Regex("^\\d{9}\$")
        return regex.containsMatchIn(data[passportIdKey] ?: error(""))
    }

}