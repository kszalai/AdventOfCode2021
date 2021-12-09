package common

import java.io.File

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