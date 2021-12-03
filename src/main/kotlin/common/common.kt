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