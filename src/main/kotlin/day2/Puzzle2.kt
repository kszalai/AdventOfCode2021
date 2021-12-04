package day2

import common.SubmarineDirection
import common.readInputFile

fun main() {
    val input = readInputFile<String>("src/main/kotlin/day2/input.txt")

    var horizontal = 0
    var vertical = 0
    var aim = 0

    input.forEach {
        val split = it.split(" ")
        val direction = split[0]
        val value = split[1].toInt()

        when (direction) {
            SubmarineDirection.forward.name -> {
                horizontal += value
                vertical += value * aim
            }
            SubmarineDirection.up.name -> aim -= value
            SubmarineDirection.down.name -> aim += value
        }
    }

    println(horizontal * vertical)
}