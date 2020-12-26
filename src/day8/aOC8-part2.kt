package day8

import java.io.File

fun main() {
    val bufferedReader = File("resources/day8/aoc-input.txt").bufferedReader()
    val lines = bufferedReader.readLines()

    fun resolveAccumulator(accumulator: Int, index: Int, maxIndex: Int, seen: MutableSet<Int>, alreadySwapped : Boolean) : Int? {
        if (index > maxIndex) {
            return accumulator
        } else {
            if (seen.contains(index)) return null
            val line = lines[index]
            when (line.substringBefore(" ")) {
                "nop" -> {
                    seen.add(index)
                    val answer = resolveAccumulator(accumulator, index+1, maxIndex, seen, alreadySwapped)
                    seen.remove(index)
                    return answer
                }
                "acc" -> {
                    seen.add(index)
                    val number = line.substringAfter(" ").toInt()
                    val answer = resolveAccumulator(accumulator + number, index+1, maxIndex, seen, alreadySwapped)
                    seen.remove(index)
                    return answer
                }
                "jmp" -> {
                    seen.add(index)
                    val number = line.substringAfter(" ").toInt()
                    val ans = resolveAccumulator(accumulator, index+number, maxIndex, seen, alreadySwapped)
                            ?: if (!alreadySwapped){
                                resolveAccumulator(accumulator, index+1, maxIndex, seen, true)
                            } else {
                                null
                            }
                    seen.remove(index)
                    return ans
                }
                else -> return null
            }

        }
    }
    val ans = resolveAccumulator(0, 0, lines.lastIndex, HashSet(), false)
    println(ans)

}
