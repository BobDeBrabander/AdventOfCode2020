package day4

import java.io.File

fun main() {
    val bufferedReader = File("resources/day4/aoc-input.txt").bufferedReader()

    val passportFields = setOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
    val currentPassportFields = mutableMapOf<String, String>()
    val passports = mutableListOf<Map<String, String>>()
    do {
        val line = bufferedReader.readLine()
        if (line.isNullOrBlank()) {
            passports.add(currentPassportFields.toMap())
            currentPassportFields.clear()
        } else {
            currentPassportFields.putAll(
                line.split(" ")
                    .map {
                        val split = it.split(":")
                        split[0] to split[1]
                    }
            )
        }
    } while (line != null)

    fun validNumber(value: String?, minVal: Int, maxVal: Int): Boolean {
        return value?.toIntOrNull()?.let { it in minVal..maxVal } ?: false
    }

    fun isValidPassport(passport: Map<String, String>): Boolean {
        if (!passport.keys.containsAll(passportFields)) return false

        if (!validNumber(passport["byr"], 1920, 2002)) return false
        if (!validNumber(passport["iyr"], 2010, 2020)) return false
        if (!validNumber(passport["eyr"], 2020, 2030)) return false
        if (passport["hgt"]?.endsWith("cm") == true) {
            if (!validNumber(passport["hgt"]?.substringBefore("cm"), 150, 193)) return false
        } else if (passport["hgt"]?.endsWith("in") == true) {
            if (!validNumber(passport["hgt"]?.substringBefore("in"), 59, 76)) return false
        } else {
            return false
        }
        val hcl = passport["hcl"]
        if (hcl == null || !hcl.startsWith("#") || hcl.length != 7 || !"([0-9]?[a-f]?)*".toRegex().matches(hcl.substring(1))) return false

        val ecl = passport["ecl"]
        if (ecl == null || ecl !in setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")) return false

        val pid = passport["pid"]
        if (pid == null || pid.length != 9 || !"([0-9])*".toRegex().matches(pid)) return false
        return true
    }

    val validPassports = passports.filter { isValidPassport(it) }
    println(validPassports.size)
}