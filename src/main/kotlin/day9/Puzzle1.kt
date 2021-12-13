package day9

import common.readInputFile

fun main() {
    val input = readInputFile<String>("src/main/kotlin/day9/input.txt")

    val matrix = input.map {
        it.map { c ->
            c.digitToInt()
        }
    }

    val computed = matrix.mapIndexed { rowIndex, row ->
        row.mapIndexed { cellIndex, cell ->
            val lowerThanLeft = if (cellIndex != 0) cell < matrix[rowIndex][cellIndex - 1] else true
            val lowerThanRight = if (cellIndex != row.size - 1) cell < matrix[rowIndex][cellIndex + 1] else true
            val lowerThanTop = if (rowIndex != 0) cell < matrix[rowIndex - 1][cellIndex] else true
            val lowerThanBottom = if (rowIndex != matrix.size - 1) cell < matrix[rowIndex + 1][cellIndex] else true
            if (lowerThanLeft && lowerThanRight && lowerThanTop && lowerThanBottom) cell + 1 else null
        }.filterNotNull()
    }.filterNot { row -> row.isEmpty() }.flatten().sum()

    println(computed)
}