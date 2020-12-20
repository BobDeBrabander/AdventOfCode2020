package day5

import java.io.File

fun main(){
    fun String.calculateSeatId() : Int{
        val beginning = this.substring(0,7)
        val ending = this.substring(7,10)

        val binaryBegin = beginning.replace('F','0').replace('B', '1')
        val binaryEnding = ending.replace('R','1').replace('L', '0')
        return Integer.parseInt(binaryBegin, 2) * 8 + Integer.parseInt(binaryEnding, 2)
    }

    val bufferedReader = File("resources/day5/aoc-input.txt").bufferedReader()
    val answer = bufferedReader.readLines().map{it.calculateSeatId()}.maxOrNull()
    println(answer)
}