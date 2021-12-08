package day6

import common.readInputFile

fun main() {
    val input = readInputFile<String>("src/main/kotlin/day6/input.txt")

    val fish = input[0].split(",").map { start -> Lanternfish(start.toInt()) } as ArrayList<Lanternfish>

    val days = 80

    for(i in 0 until days) {
        val newFish = ArrayList<Lanternfish?>()
        fish.forEach {
            newFish.add(it.countDay())
        }
        fish.addAll(newFish.filterNotNull())
    }

    println(fish.size)
}

class Lanternfish(private val startOfCycle: Int) {
    var cycle = startOfCycle

    fun countDay(): Lanternfish? {
        var newFish: Lanternfish? = null
        if (cycle == 0) {
            cycle = 7
            newFish = reproduce()
        }
        cycle--
        return newFish
    }

    private fun reproduce(): Lanternfish {
        return Lanternfish(8)
    }
}