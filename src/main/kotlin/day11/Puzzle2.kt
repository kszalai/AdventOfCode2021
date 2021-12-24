package day11

import common.readIntMatrix

fun main() {
    var input = readIntMatrix("src/main/kotlin/day11/input.txt")

    var steps = 0

    while (!Day11Puzzle2.allSquidsFlashed(input)) {
        input = input.map(Day11Puzzle2::increaseRowBy1)
        input = Day11Puzzle2.checkNearbySquids(
            input,
            input.map { row -> row.map { false }.toMutableList() }.toMutableList()
        )
        steps++
    }

    input.forEach {
        println(it)
    }

    println(steps)
}

object Day11Puzzle2 {

    var flashes = 0

    fun allSquidsFlashed(matrix: List<List<Int>>): Boolean {
        return matrix == matrix.map { row -> row.map { 0 } }
    }

    fun increaseRowBy1(row: List<Int>) = row.map(::increaseBy1)

    fun increaseBy1(cell: Int) = cell + 1

    fun checkNearbySquids(matrix: List<List<Int>>, visited: MutableList<MutableList<Boolean>>): List<List<Int>> {
        var modified = matrix.map { row ->
            row.toMutableList()
        }.toMutableList()

        val pointsAbove9 = modified.mapIndexed { i, row ->
            row.mapIndexed { j, cell ->
                if (cell > 9 && !visited[i][j]) Pair(i, j) else null
            }.filterNotNull()
        }.flatten().toMutableList()

        if (pointsAbove9.isNotEmpty()) {
            pointsAbove9.map {
                visited[it.first][it.second] = true
                // Check up
                if (it.first - 1 >= 0) {
                    modified[it.first - 1][it.second]++
                }
                // Check down
                if (it.first + 1 < matrix.size) {
                    modified[it.first + 1][it.second]++
                }
                // Check left
                if (it.second - 1 >= 0) {
                    modified[it.first][it.second - 1]++
                }
                // Check right
                if (it.second + 1 < matrix[it.first].size) {
                    modified[it.first][it.second + 1]++
                }
                // Check up && left
                if (it.first - 1 >= 0 && it.second - 1 >= 0) {
                    modified[it.first - 1][it.second - 1]++
                }
                // Check up && right
                if (it.first - 1 >= 0 && it.second + 1 < matrix[it.first - 1].size) {
                    modified[it.first - 1][it.second + 1]++
                }
                // Check down && left
                if (it.first + 1 < matrix.size && it.second - 1 >= 0) {
                    modified[it.first + 1][it.second - 1]++
                }
                // Check down && right
                if (it.first + 1 < matrix.size && it.second + 1 < matrix[it.first + 1].size) {
                    modified[it.first + 1][it.second + 1]++
                }
            }
            modified = checkNearbySquids(modified, visited).map { row -> row.toMutableList() }.toMutableList()
        }

        return modified.map { row ->
            row.map { cell ->
                if (cell > 9) {
                    flashes++
                    0
                } else cell
            }
        }
    }
}