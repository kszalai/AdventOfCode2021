import java.io.File

fun main() {
    val lineList = ArrayList<Int>()

    File("input.txt").useLines { lines ->
        lines.forEach {
            lineList.add(it.toInt())
        }
    }

    val windows = ArrayList<Int>()

    var value1: Int
    var value2: Int
    var value3: Int

    for (i in 2..lineList.size-1) {
        value1 = lineList[i - 2]
        value2 = lineList[i - 1]
        value3 = lineList[i]
        windows.add(value1 + value2 + value3)
    }

    var amountOfIncreases = 0
    var previousValue = -1

    windows.forEach {
        if (previousValue == -1) {
            previousValue = it
            return@forEach
        }

        val diff = it - previousValue

        amountOfIncreases += if (diff > 0) 1 else 0

        previousValue = it
    }

    println(amountOfIncreases)
}