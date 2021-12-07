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
        val pointA = coords[0]
        val pointB = coords[1]
        when {
            pointA.first == pointB.first -> {
                Pair(
                    if (pointA.second < pointB.second) pointA else pointB,
                    if (pointA.second < pointB.second) pointB else pointA
                )
            }
            pointA.second == pointB.second -> {
                Pair(
                    if (pointA.first < pointB.first) pointA else pointB,
                    if (pointA.first < pointB.first) pointB else pointA
                )
            }
            else -> {
                Pair(pointA, pointB)
            }
        }
    }.filter {
        it.first.first == it.second.first ||
                it.first.second == it.second.second
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
        val pointA = it.first
        val pointB = it.second
        when {
            pointA.first == pointB.first -> {
                for(i in pointA.second..pointB.second) {
                    matrix[pointA.first][i]++
                }
            }
            pointA.second == pointB.second -> {
                for(i in pointA.first..pointB.first) {
                    matrix[i][pointA.second]++
                }
            }
        }
    }

    var total = 0
    matrix.map { row -> row.filter { cell -> cell > 1 } }.filterNot { row -> row.isEmpty() }.forEach { row ->
        total += row.size
    }

    println(total)
}