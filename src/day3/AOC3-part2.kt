package day3

import java.io.File

fun main(){
    val bufferedReader = File("resources/day3/aoc-input.txt").bufferedReader()
    val grid = bufferedReader.readLines().map{ it.toCharArray().toList() }
    val xLength = grid[0].size

    fun doRun(xOffset: Int, yOffset: Int) : Int {
        var x = 0
        var y = 0
        var sum = 0
        do {
            if (grid[y][x] == '#') sum++
            x = (x+xOffset)%xLength
            y += yOffset
        } while(y < grid.size)
        return sum
    }
    val answer = doRun(1,1)*doRun(3,1)*doRun(5,1)*doRun(7,1)*doRun(1,2)
    println(answer)
}