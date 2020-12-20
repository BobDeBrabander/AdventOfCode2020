package day19

import java.io.File

fun main() {
    val bufferedReader = File("resources/day19/inputFile2.txt").bufferedReader()
    val rules = mutableListOf<String>()
    val words = mutableListOf<String>()
    do {
        val line = bufferedReader.readLine(); if (line != "") rules.add(line)
    } while (line != "")
    do {
        val line = bufferedReader.readLine(); if (line != null) words.add(line)
    } while (line != null)

    val ruleNodes = parseRules(rules.toList())
    fun RuleNode.calculateRegex(): String {
        if (parsedRegex == null) {
            if (rule.startsWith("\"")) {
                this.parsedRegex = rule.substring(1, rule.length - 1)
            } else {
                var seenPlus = false
                val ruleParsed = rule.split(" ").map {
                    when (it) {
                        "|" -> it
                        "+" -> {
                            seenPlus = true
                            ""
                        }
                        "(" -> "("
                        ")?" -> ")?"
                        else -> ruleNodes[it.toInt()].calculateRegex()
                    }
                }.joinToString("")
                this.parsedRegex = "($ruleParsed)"
                if (seenPlus) this.parsedRegex+="+"
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

fun parseRules(inputRules: List<String>): List<RuleNode> {
    return inputRules.map {
        val firstSpaceIndex = it.indexOfFirst { char -> (char == ' ') }
        val ruleNumber = it.substring(0, firstSpaceIndex - 1).toInt()
        val rule = when (ruleNumber) {
            8 -> {
                "42 +"
            }
            11 -> {
                "42 ( 42 ( 42 ( 42 ( 42 ( 42 ( 42 ( 42 ( 42 31 )? 31 )? 31 )? 31 )? 31 )? 31 )? 31 )? 31 )? 31"
            }
            else -> {
                it.substring(firstSpaceIndex + 1)
            }
        }
        RuleNode(ruleNumber, rule)
    }.sortedBy { it.ruleNumber }
}

data class RuleNode(
        val ruleNumber: Int,
        var rule: String
) {
    var parsedRegex: String? = null
}