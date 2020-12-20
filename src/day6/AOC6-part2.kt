package day6

import java.io.File

fun main(){
    val bufferedReader = File("resources/day6/aoc-input.txt").bufferedReader()

    var groups = mutableSetOf<Char>()
    var startOfGroup = true
    var sum = 0
    do {
        val line = bufferedReader.readLine()
        if (line.isNullOrBlank()) {
            sum += groups.size
            groups.clear()
            startOfGroup = true
        } else {
            if (startOfGroup) {
                groups = line.toCharArray().toMutableSet()
                startOfGroup = false
            } else {
                val thisAns = line.toCharArray()
                groups = groups.filter { thisAns.contains(it) }.toMutableSet()
            }
        }
    } while (line != null)
    println(sum)
}