package day8

import java.io.File

fun main() {
    val bufferedReader = File("resources/day8/aoc-input.txt").bufferedReader()
    val lines = bufferedReader.readLines()

    val indexesSeen = HashSet<Int>()
    var accumulator = 0
    var index = 0
    while (!indexesSeen.contains(index)){
        indexesSeen.add(index)
        val line = lines[index]
        val instr = line.substringBefore(" ")
        when (instr){
            "nop" -> {
                index++;
            }
            "acc" -> {
                accumulator += line.substringAfter(" ").toInt()
                index++;
            }
            "jmp" -> {
                index += line.substringAfter(" ").toInt()
            }
        }
    }
    println(accumulator)
}
