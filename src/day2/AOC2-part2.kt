package day2

import java.io.File

fun main(){
    val bufferedReader = File("resources/day2/aoc-input.txt").bufferedReader()
    val lines = bufferedReader.readLines()
    val validLines = lines.filter{
        val parts = it.split(" ")
        val counts = parts[0].split("-").map{it.toInt()}
        val letter = parts[1][0]
        val password = parts[2]
        val firstHit = password.getOrNull(counts[0]-1)?.let{it == letter} ?: false
        val secondHit = password.getOrNull(counts[1]-1)?.let{it == letter} ?: false
        firstHit xor secondHit
    }
    println(validLines.size)
}