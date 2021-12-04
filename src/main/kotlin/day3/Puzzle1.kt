package day3

import common.readInputFile
import java.lang.Integer.parseInt

fun main() {
    val input = readInputFile<String>("src/main/kotlin/day3/input.txt")

    var gamma = ""
    var epsilon = ""

    val binLength = input[0].length - 1

    for(i in 0..binLength) {
        var zero = 0
        var one = 0
        input.forEach {
            when (it[i]) {
                '0' -> zero++
                '1' -> one++
            }
        }
        gamma += if (zero > one) "0" else "1"
        epsilon += if (zero < one) "0" else "1"
    }

    val gamDec = parseInt(gamma, 2)
    val epsDec = parseInt(epsilon, 2)

    println(gamDec * epsDec)
}