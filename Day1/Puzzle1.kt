import java.io.File

fun main() {
    val lineList = ArrayList<Int>()

    File("input.txt").useLines { lines -> 
        lines.forEach { 
            lineList.add(it.toInt())
        }
    }

    var amountOfIncreases = 0
    var previousValue = -1

    lineList.forEach {
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