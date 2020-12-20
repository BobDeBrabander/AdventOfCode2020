package day1

import java.io.File

fun main() {
    val bufferedReader = File("resources/day1/aoc-input.txt").bufferedReader()
    val numbers = bufferedReader.readLines().map { it.toInt() }
    numbers.forEachIndexed { index1, i ->
        numbers.forEachIndexed { index2, j ->
            numbers.forEachIndexed { index3, k ->
                if (index1 != index2 && index2!=index3 && index3!=index1 && i + j + k == 2020) {
                    println(i * j * k)
                    return
                }
            }
        }
    }

}