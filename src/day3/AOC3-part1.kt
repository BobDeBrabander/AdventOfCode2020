package day3

import java.io.File

fun main(){
    val bufferedReader = File("resources/day3/aoc-input.txt").bufferedReader()
    val grid = bufferedReader.readLines().map{ it.toCharArray().toList() }
    val xLength = grid[0].size
    var x = 0
    var y = 0
    var sum = 0
    do {
        if (grid[y][x] == '#') sum++
        x = (x+3)%xLength
        y++
    } while(y < grid.size)
    println(sum)
}