package day19

import java.io.File

fun main() {
    val bufferedReader = File("resources/day19/aoc-input.txt").bufferedReader()
    val rules = mutableListOf<String>()
    val words = mutableListOf<String>()
    do {
        val line = bufferedReader.readLine(); if (line != "") rules.add(line)
    } while (line != "")
    do {
        val line = bufferedReader.readLine(); if (line != null) words.add(line)
    } while (line != null)

    val ruleNodes = parseRulesPart1(rules.toList())
    fun RuleNode.calculateRegex(): String {
        if (parsedRegex == null) {
            if (rule.startsWith("\"")) {
                this.parsedRegex = rule.substring(1, rule.length - 1)
            } else {
                val ruleParsed = rule.split(" ").map {
                    when (it) {
                        "|" -> it
                        else -> ruleNodes[it.toInt()].calculateRegex()
                    }
                }.joinToString("")
                this.parsedRegex = "($ruleParsed)"
            }
        }
        return parsedRegex!!
    }

    val regex = ruleNodes[0].calculateRegex()
    val regexPattern = regex.toRegex()
    var counter = 0
    words.forEach {
        if (regexPattern.matches(it)) counter++
    }
    println(regex)
    println(counter)
}

fun parseRulesPart1(inputRules: List<String>): List<RuleNode> {
    return inputRules.map {
        val firstSpaceIndex = it.indexOfFirst { char -> (char == ' ') }
        val ruleNumber = it.substring(0, firstSpaceIndex - 1).toInt()
        val rule = it.substring(firstSpaceIndex + 1)
        RuleNode(ruleNumber, rule)
    }.sortedBy { it.ruleNumber }
}