package common

import java.io.File
import java.lang.Integer.parseInt

fun <T> readInputFile(path: String): ArrayList<T> {
    val lineList = ArrayList<T>()

    File(path).useLines { lines ->
        lines.forEach {
            lineList.add(it as T)
        }
    }

    return lineList
}

fun readIntCSVInputFile(path: String): ArrayList<Int> {
    return File(path).bufferedReader().use { reader -> reader.readLine() }.split(",").map { number -> number.toInt() } as ArrayList<Int>
}

fun readIntMatrix(path: String): List<List<Int>> {
    val matrix = mutableListOf<List<Int>>()

    File(path).useLines { lines ->
        lines.forEach {
            val row = mutableListOf<Int>()
            it.map { digit -> row.add(parseInt(digit.toString())) }
            matrix.add(row)
        }
    }

    return matrix
}