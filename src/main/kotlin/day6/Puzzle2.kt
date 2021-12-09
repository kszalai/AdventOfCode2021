package day6

import common.readIntCSVInputFile

fun main() {
    val input = readIntCSVInputFile("src/main/kotlin/day6/input.txt")
    val fish = arrayListOf(
        input.filter { fish -> fish == 0 }.size.toDouble(),
        input.filter { fish -> fish == 1 }.size.toDouble(),
        input.filter { fish -> fish == 2 }.size.toDouble(),
        input.filter { fish -> fish == 3 }.size.toDouble(),
        input.filter { fish -> fish == 4 }.size.toDouble(),
        input.filter { fish -> fish == 5 }.size.toDouble(),
        input.filter { fish -> fish == 6 }.size.toDouble(),
        input.filter { fish -> fish == 7 }.size.toDouble(),
        input.filter { fish -> fish == 8 }.size.toDouble()
    )

    val days = 256

    for (i in 0 until days) {
        val newFish = fish[0]
        for (j in 1 until fish.size) {
            fish[j - 1] = fish[j]
        }
        fish[fish.size - 3] += newFish
        fish[fish.size - 1] = newFish
    }

    println(fish.sum().toBigDecimal())
}