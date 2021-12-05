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

    val winningBoards = ArrayList<BingoBoard>()
    var winningNumber: Int = -1

    var newWinners = ArrayList<BingoBoard>()
    bingoCalls.forEach forEachCalls@{ call ->
        bingoBoards.forEach { board ->
            board.checkNumberInBoard(call)
            if (board.checkForBingo()) {
                newWinners.add(board)
                winningNumber = call
            }
        }
        if (newWinners.size != 0) {
            newWinners.forEach {
                winningBoards.add(it)
                bingoBoards.remove(it)
            }
            newWinners = ArrayList()
        }
    }

    require(winningNumber != -1)
    println(winningBoards.last().calculateAnswer(winningNumber))

}