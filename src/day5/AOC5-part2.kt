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
    val seats = bufferedReader.readLines().map{it.calculateSeatId()}.toSet()
    val min = seats.minOrNull()!!
    val max = seats.maxOrNull()!!
    for (i in min..max) {
        if(!seats.contains(i)) println(i)
    }

}