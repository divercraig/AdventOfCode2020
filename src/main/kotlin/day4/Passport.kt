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
    private val countryIdKey = "cid"

    init {
        data = HashMap()
        val fields = passportString.split(' ')
        for(field in fields) {
            val parts = field.split(':')
            assert(parts.size == 2)
            data[parts[0]] = parts[1]
        }
    }

    fun isValid(): Boolean {
        return data.containsKey(birthYearKey)
                && data.containsKey(issueYearKey)
                && data.containsKey(expirationYearKey)
                && data.containsKey(heightKey)
                && data.containsKey(hairColorKey)
                && data.containsKey(eyeColorKey)
                && data.containsKey(passportIdKey)
    }

}