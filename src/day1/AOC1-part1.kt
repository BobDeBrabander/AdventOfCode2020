package day1

import java.io.File

fun main(){
    val bufferedReader = File("resources/day1/aoc-input.txt").bufferedReader()
    val numbers = bufferedReader.readLines().map{it.toInt()}
    numbers.forEachIndexed { index1, i ->
        numbers.forEachIndexed{ index2, j ->
            if (index1 != index2 && i + j == 2020){
                println(i*j)
                return
            }
        }
    }

}