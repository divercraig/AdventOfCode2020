package day4

import java.io.File

class PassportLoader(filename: String) {
    private val passports: MutableList<Passport> = ArrayList();

    init {
        var currentPassport = ""
        File(filename).forEachLine {
            if (it.length == 0)
            {
                passports.add(Passport(currentPassport))
                currentPassport = ""
            } else {
                if(currentPassport.length > 0) currentPassport += " "
                currentPassport += it

            }
        }
        passports.add(Passport(currentPassport))
    }

    fun countValidPassports(): Int {
        var validPassports = 0
        for(passport in passports) {
            if (passport.isValid()) validPassports++
        }
        return validPassports
    }
}