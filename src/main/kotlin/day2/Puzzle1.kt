package day2

import common.SubmarineDirection
import common.readInputFile

fun main() {
    val input = readInputFile<String>("src/main/kotlin/day2/input.txt")

    var horizontal = 0
    var vertical = 0

    input.forEach {
        val split = it.split(" ")
        val direction = split[0]
        val value = split[1].toInt()

        when (direction) {
            SubmarineDirection.forward.name -> horizontal += value
            SubmarineDirection.up.name -> vertical -= value
            SubmarineDirection.down.name -> vertical += value
        }
    }

    println(horizontal * vertical)
}