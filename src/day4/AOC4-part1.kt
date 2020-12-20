package day4

import java.io.File

fun main(){
    val bufferedReader = File("resources/day4/aoc-input.txt").bufferedReader()

    val passportFields = setOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
    val currentPassportFields = mutableSetOf<String>()
    val passports = mutableListOf<Set<String>>()
    do {
        val line = bufferedReader.readLine()
        if (line.isNullOrBlank()) {
            passports.add(currentPassportFields.toSet())
            currentPassportFields.clear()
        } else {
            currentPassportFields.addAll(line.split(" ").map{it.substringBefore(":")})
        }
    } while (line != null)
    val validPassports = passports.filter { it.containsAll(passportFields) }
    println(validPassports.size)
}