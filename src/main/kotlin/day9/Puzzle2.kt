package day9

import common.readInputFile

fun main() {
    val input = readInputFile<String>("src/main/kotlin/day9/input.txt")

    val matrix = input.map {
        it.map { c ->
            c.digitToInt()
        }
    }

    val lowPoints = matrix.mapIndexed { rowIndex, row ->
        row.mapIndexed { cellIndex, cell ->
            val lowerThanLeft = if (cellIndex != 0) cell < matrix[rowIndex][cellIndex - 1] else true
            val lowerThanRight = if (cellIndex != row.size - 1) cell < matrix[rowIndex][cellIndex + 1] else true
            val lowerThanTop = if (rowIndex != 0) cell < matrix[rowIndex - 1][cellIndex] else true
            val lowerThanBottom = if (rowIndex != matrix.size - 1) cell < matrix[rowIndex + 1][cellIndex] else true
            if (lowerThanLeft && lowerThanRight && lowerThanTop && lowerThanBottom) Pair(rowIndex, cellIndex) else null
        }.filterNotNull()
    }.filterNot { row -> row.isEmpty() }.flatten()

    val basinSizes = mutableMapOf<Int, Int>()

    val basins = matrix.map { row ->
        row.map { cell ->
            if (cell == 9) 0 else -1
        }
    } as ArrayList<ArrayList<Int>>

    lowPoints.forEachIndexed { index, lowPoint ->
        val visited = matrix.map { row ->
            row.map { false }
        } as ArrayList<ArrayList<Boolean>>
        visited[lowPoint.first][lowPoint.second] = true
        // The process of exploring the basin ignores the starting point,
        // so add 1 to count the lowest point in the basin
        basins[lowPoint.first][lowPoint.second] = index + 1
        exploreBasin(lowPoint, matrix, basins, index + 1)
    }

    basins.map { row ->
        row.filterNot {
            it == 0 || it == -1
        }.map {
            basinSizes[it] = (basinSizes[it] ?: 0) + 1
        }
    }

    println(basinSizes.toList()
        .sortedByDescending { it.second }
        .take(3)
        .map { element -> element.second }
        .reduce { acc, i -> acc * i }
    )

}

fun exploreBasin(point: Pair<Int, Int>, matrix: List<List<Int>>, basins: ArrayList<ArrayList<Int>>, index: Int) {
    val pointValue = matrix[point.first][point.second]

    // Explore Up
    if (point.first - 1 >= 0) {
        if (isDiff1(
                criteria = pointValue < matrix[point.first - 1][point.second],
                valueB = matrix[point.first - 1][point.second],
                basins = basins,
                x = point.first - 1,
                y = point.second
            )
        ) {
            basins[point.first - 1][point.second] = index
            exploreBasin(Pair(point.first - 1, point.second), matrix, basins, index)
        }
    }

    // Explore Down
    if (point.first + 1 < matrix.size) {
        if (isDiff1(
                criteria = pointValue < matrix[point.first + 1][point.second],
                valueB = matrix[point.first + 1][point.second],
                basins = basins,
                x = point.first + 1,
                y = point.second
            )
        ) {
            basins[point.first + 1][point.second] = index
            exploreBasin(Pair(point.first + 1, point.second), matrix, basins, index)
        }
    }

    // Explore Left
    if (point.second - 1 >= 0) {
        if (isDiff1(
                criteria = pointValue < matrix[point.first][point.second - 1],
                valueB = matrix[point.first][point.second - 1],
                basins =basins,
                x = point.first,
                y = point.second - 1
            )
        ) {
            basins[point.first][point.second - 1] = index
            exploreBasin(Pair(point.first, point.second - 1), matrix, basins, index)
        }
    }

    // Explore Right
    if (point.second + 1 < matrix[point.first].size) {
        if (isDiff1(
                criteria = pointValue < matrix[point.first][point.second + 1],
                valueB = matrix[point.first][point.second + 1],
                basins = basins,
                x = point.first,
                y = point.second + 1
            )
        ) {
            basins[point.first][point.second + 1] = index
            exploreBasin(Pair(point.first, point.second + 1), matrix, basins, index)
        }
    }
}

fun isDiff1(criteria: Boolean, valueB: Int, basins: ArrayList<ArrayList<Int>>, x: Int, y: Int): Boolean {
    return criteria && valueB != 9 && basins[x][y] == -1
}