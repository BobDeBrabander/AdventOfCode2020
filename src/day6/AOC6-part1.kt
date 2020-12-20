package day6

import java.io.File

fun main(){
    val bufferedReader = File("resources/day6/aoc-input.txt").bufferedReader()

    val groups = mutableSetOf<Char>()
    var sum = 0
    do {
        val line = bufferedReader.readLine()
        if (line.isNullOrBlank()) {
            sum += groups.size
            groups.clear()
        } else {
            groups.addAll(line.toCharArray().toList())
        }
    } while (line != null)
    println(sum)
}