package day3

import common.readInputFile
import java.lang.Integer.parseInt

fun main() {
    val input = readInputFile<String>("src/main/kotlin/day3/input.txt")

    var oxygenRating = input
    var co2rating = input

    val binLength = input[0].length - 1

    for (i in 0..binLength) {
        var oxygenZero = 0
        var oxygenOne = 0
        if (oxygenRating.size != 1) {
            oxygenRating.forEach {
                when (it[i]) {
                    '0' -> oxygenZero++
                    '1' -> oxygenOne++
                }
            }
            oxygenRating = (oxygenRating.filter { element ->
                element[i] == (if (oxygenZero == oxygenOne) '1' else (if (oxygenZero > oxygenOne) '0' else '1'))
            }) as ArrayList<String>
        }
        var co2Zero = 0
        var co2One = 0
        if (co2rating.size != 1) {
            co2rating.forEach {
                when (it[i]) {
                    '0' -> co2Zero++
                    '1' -> co2One++
                }
            }
            co2rating = (co2rating.filter { element ->
                element[i] == (if (co2Zero == co2One) '0' else (if (co2Zero < co2One) '0' else '1'))
            }) as ArrayList<String>
        }
    }

    require(oxygenRating.size == 1)
    require(co2rating.size == 1)

    val oxygenDec = parseInt(oxygenRating[0], 2)
    val co2Dec = parseInt(co2rating[0], 2)

    println(oxygenDec * co2Dec)
}