package day7

import common.readIntCSVInputFile
import kotlin.math.abs

fun main() {
    val input = readIntCSVInputFile("src/main/kotlin/day7/input.txt")

    val min = input.minOrNull() ?: -1
    val max = input.maxOrNull() ?: -1

    require(min != -1)
    require(max != -1)

    var smallestFuel = -1

    for (i in min..max) {
        val totalFuel = input.sumOf { position ->
            triangularNumber(abs(position - i))
        }
        if (smallestFuel == -1 || totalFuel < smallestFuel) {
            smallestFuel = totalFuel
        }
    }

    println(smallestFuel)
}

fun triangularNumber(target: Int): Int {
    return (target * (target + 1)) / 2
}