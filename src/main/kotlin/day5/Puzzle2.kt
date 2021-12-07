package day5

import common.readInputFile

fun main() {
    val input = readInputFile<String>("src/main/kotlin/day5/input.txt")

    val lines = input.map { line ->
        val coords = line.split(" -> ").map { pair ->
            val values = pair.split(",").map {
                it.toInt()
            }
            Pair(values[0], values[1])
        }
        Pair(coords[0], coords[1])
    }

    val maxX1 = lines.maxOf { line -> line.first.first }
    val maxX2 = lines.maxOf { line -> line.second.first }
    val maxY1 = lines.maxOf { line -> line.first.second }
    val maxY2 = lines.maxOf { line -> line.second.second }

    val matrix = ArrayList<ArrayList<Int>>().apply {
        for (i in 0..maxOf(maxX1, maxX2)) {
            add(ArrayList<Int>().apply {
                for (j in 0..maxOf(maxY1, maxY2)) {
                    add(0)
                }
            })
        }
    }

    lines.forEach {
        val start = it.first
        val end = it.second
        val diffX = start.first - end.first
        val diffY = start.second - end.second
        var currentX = start.first
        var currentY = start.second

        while (currentX != end.first || currentY != end.second) {
            matrix[currentX][currentY]++
            currentX += if (currentX != end.first) (if (diffX > 0) -1 else 1) else 0
            currentY += if (currentY != end.second) (if (diffY > 0) -1 else 1) else 0
        }
        matrix[end.first][end.second]++
    }

    var total = 0
    matrix.map { row -> row.filter { cell -> cell > 1 } }.filterNot { row -> row.isEmpty() }.forEach { row ->
        total += row.size
    }

    println(total)
}