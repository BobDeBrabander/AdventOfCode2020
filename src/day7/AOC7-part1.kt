package day7

import java.io.File

fun main() {
    val bufferedReader = File("resources/day7/aoc-input.txt").bufferedReader()

    val bags = mutableMapOf<String, List<String>>()
    fun processLine(line: String) {
        val part = line.split(" contain ")
        val thisBag = part[0].substringBefore(" bag")
        if (part[1] == "no other bags.") {
            bags[thisBag] = listOf()
            return
        }
        val inThisBag = part[1].split(", ")
                .map { it.substringBefore(" bag") }
                .map { it.substringAfter(" ") }
        bags[thisBag] = inThisBag
    }

    val lines = bufferedReader.readLines()
    lines.forEach { processLine(it) }

    fun containsShinyBagEventually(bagName: String): Boolean {
        val contains = bags[bagName]?.any { it == "shiny gold" } ?: false
        val nestedContains = bags[bagName]?.any { name ->
            containsShinyBagEventually(name)
        } ?: false
        return contains || nestedContains
    }

    val answer = bags.keys.filter { containsShinyBagEventually(it) }
    println(answer.size)
}