package day8

import common.readInputFile

/**
 * Notes
 * 7 Segment display translations
 * 0 - 6
 * 1 - 2
 * 2 - 5
 * 3 - 5
 * 4 - 4
 * 5 - 5
 * 6 - 6
 * 7 - 3
 * 8 - 7
 * 9 - 6
 */
fun main() {
    val input = readInputFile<String>("src/main/kotlin/day8/input.txt")

    println(input.map {
        it.split("|")[1].trim()
    }.sumOf {
        it.split(" ").filter { outputValue ->
            outputValue.length == 2 ||
                    outputValue.length == 4 ||
                    outputValue.length == 3 ||
                    outputValue.length == 7
        }.size
    })
}