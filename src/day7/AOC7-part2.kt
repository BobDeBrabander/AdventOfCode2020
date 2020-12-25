package day7

import java.io.File

fun main() {
    val bufferedReader = File("resources/day7/aoc-input.txt").bufferedReader()

    val bags = mutableMapOf<String, Map<String, Int>>()
    fun processLine(line: String) {
        val part = line.split(" contain ")
        val thisBag = part[0].substringBefore(" bag")
        if (part[1] == "no other bags.") {
            bags[thisBag] = mapOf()
            return
        }
        val inThisBag = part[1].split(", ")
                .map { it.substringBefore(" bag") }
                .map { it.substringAfter(" ") to it.substringBefore(" ").toInt() }
                .toMap()
        bags[thisBag] = inThisBag
    }

    val lines = bufferedReader.readLines()
    lines.forEach { processLine(it) }

    fun calculateBagCount(bagName: String, multiplier: Int) : Int{
        val baseCount = multiplier * (bags[bagName]?.values?.sum() ?: 0)
        val nestedCount = bags[bagName]?.map { (name, count) ->
            calculateBagCount(name, multiplier*count)
        }?.sum() ?: 0
        return baseCount + nestedCount
    }

    val answer = calculateBagCount("shiny gold", 1)
    println(answer)
}