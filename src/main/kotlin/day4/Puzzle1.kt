package day4

import common.readInputFile

fun main() {
    val input = readInputFile<String>("src/main/kotlin/day4/input.txt")

    val bingoCalls = input[0].split(",").map { it.toInt() }
    input.removeAt(0) // Remove calls
    input.removeAt(0) // Remove spacer row

    val bingoBoards = ArrayList<BingoBoard>()

    var currentBingoBoard = ArrayList<ArrayList<Int>>()
    input.forEachIndexed { index, bingoRow ->
        if (bingoRow.isNotEmpty()) {
            currentBingoBoard.add(
                bingoRow.trim()
                    .split(" ")
                    .filter { element -> element != "" }
                    .map { number -> number.toInt() } as ArrayList<Int>)
        }

        if ((bingoRow.isEmpty() && currentBingoBoard.isNotEmpty()) || index == input.size - 1) {
            bingoBoards.add(BingoBoard(currentBingoBoard))
            currentBingoBoard = ArrayList()
            return@forEachIndexed
        }
    }

    var winner: BingoBoard? = null
    var winningNumber: Int = -1

    bingoCalls.forEach forEachCalls@{ call ->
        bingoBoards.forEach { board ->
            if (winner == null) {
                board.checkNumberInBoard(call)
                if (board.checkForBingo()) {
                    winner = board
                    winningNumber = call
                    return@forEachCalls
                }
            }
        }
    }

    require(winningNumber != -1)
    println(requireNotNull(winner).calculateAnswer(winningNumber))

}

class BingoBoard(private val board: ArrayList<ArrayList<Int>>) {
    private val marked: ArrayList<ArrayList<Boolean>> =
        board.map { row -> row.map { false } } as ArrayList<ArrayList<Boolean>>

    fun checkNumberInBoard(call: Int) {
        val contains = board.any { row -> row.any { cell -> cell == call } }
        if (contains) {
            val rowWithCall = board.find { row -> row.any { cell -> cell == call } }
            val indexOfCell = requireNotNull(rowWithCall).indexOf(call)
            val indexOfRow = board.indexOf(rowWithCall)
            marked[indexOfRow][indexOfCell] = true
        }
    }

    fun checkForBingo(): Boolean {
        val hasHorizontal = marked.map { row ->
            (row.filter { cell -> cell }.size) == row.size
        }.any { row -> row }
        var hasVertical = false
        for (i in 0 until marked.size) {
            hasVertical = (marked.map { row -> row[i] }.filter { cell -> cell }.size) == marked.size
        }

        return hasHorizontal || hasVertical
    }

    fun calculateAnswer(winningNumber: Int): Int {
        var sum = 0
        board.mapIndexed { rowIndex, row ->
            row.mapIndexed { cellIndex, cell ->
                sum += if (!marked[rowIndex][cellIndex]) cell else 0
            }
        }
        return sum * winningNumber
    }
}