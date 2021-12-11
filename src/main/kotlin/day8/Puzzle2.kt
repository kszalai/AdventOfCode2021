package day8

import common.readInputFile

/**
 * Notes
 * 7 Segment display translations
 * 0 - 6
 * 1 - 2
 * 2 - 5
 * 3 - 5
 * 4 - 4
 * 5 - 5
 * 6 - 6
 * 7 - 3
 * 8 - 7
 * 9 - 6
 */
fun main() {
    val input = readInputFile<String>("src/main/kotlin/day8/input.txt")

    println(input.map {
        it.split("|")
    }.sumOf {
        val uniqueDigits = it[0].trim().split(" ")
        SegmentDisplay(
            digit1 = requireNotNull(uniqueDigits.find { digit -> digit.length == 2 }),
            digit4 = requireNotNull(uniqueDigits.find { digit -> digit.length == 4 }),
            digit7 = requireNotNull(uniqueDigits.find { digit -> digit.length == 3 }),
            digit8 = requireNotNull(uniqueDigits.find { digit -> digit.length == 7 }),
            digits = uniqueDigits as ArrayList<String>
        ).translateOutputValue(it[1])
    })
}

class SegmentDisplay(
    val digit1: String,
    val digit4: String,
    val digit7: String,
    val digit8: String,
    val digits: ArrayList<String>
) {
    private val topTopSegment: Char
    private val topLeftSegment: Char
    private val topRightSegment: Char
    private val middleSegment: Char
    private val bottomLeftSegment: Char
    private val bottomRightSegment: Char
    private val bottomBottomSegment: Char

    init {
        bottomRightSegment = digits.filterNot {
            it == digit1 ||
                    it == digit7 ||
                    it == digit4 ||
                    it == digit8
        }.filterNot {
            it.contains(digit1[0]) &&
                    it.contains(digit1[1])
        }.filter {
            it.length == 6
        }[0].filter { chars ->
            chars == digit1[0] ||
                    chars == digit1[1]
        }[0]
        topRightSegment = digit1.filter { chars -> chars != bottomRightSegment }[0]
        topTopSegment = digit7.filter { chars -> chars != topRightSegment && chars != bottomRightSegment }[0]
        middleSegment = digits.filterNot {
            it == digit1 ||
                    it == digit7 ||
                    it == digit4 ||
                    it == digit8
        }.filter {
            it.contains(topRightSegment) &&
                    it.contains(bottomRightSegment) &&
                    it.contains(topTopSegment) &&
                    it.length == 5
        }[0].filter {
            digit4.contains(it) &&
                    it != topRightSegment &&
                    it != bottomRightSegment &&
                    it != topTopSegment
        }[0]
        topLeftSegment =
            digit4.filter { chars -> chars != topRightSegment && chars != bottomRightSegment && chars != middleSegment }[0]
        bottomBottomSegment = digits.filterNot {
            it == digit1 ||
                    it == digit4 ||
                    it == digit7 ||
                    it == digit8
        }.filter {
            it.contains(topRightSegment) &&
                    it.contains(bottomRightSegment) &&
                    it.contains(topTopSegment) &&
                    it.contains(topLeftSegment) &&
                    it.contains(middleSegment)
        }[0].filter {
            it != topRightSegment &&
                    it != bottomRightSegment &&
                    it != topTopSegment &&
                    it != topLeftSegment &&
                    it != middleSegment
        }[0]
        bottomLeftSegment = digit8.filter { chars ->
            chars != topRightSegment &&
                    chars != bottomRightSegment &&
                    chars != topTopSegment &&
                    chars != topLeftSegment &&
                    chars != middleSegment &&
                    chars != bottomBottomSegment
        }[0]
    }

    fun translateOutputValue(outputValues: String): Int {
        var outputValueString = ""
        outputValues.trimStart().trim().split(" ").map { output ->

            var hasTopTop = false
            var hasTopLeft = false
            var hasTopRight = false
            var hasMiddle = false
            var hasBottomLeft = false
            var hasBottomRight = false
            var hasBottomBottom = false

            output.map {
                when (it) {
                    topTopSegment -> hasTopTop = true
                    topLeftSegment -> hasTopLeft = true
                    topRightSegment -> hasTopRight = true
                    middleSegment -> hasMiddle = true
                    bottomLeftSegment -> hasBottomLeft = true
                    bottomRightSegment -> hasBottomRight = true
                    bottomBottomSegment -> hasBottomBottom = true
                }
            }

            when {
                hasTopTop && hasTopLeft && hasTopRight && !hasMiddle && hasBottomLeft && hasBottomRight && hasBottomBottom -> "0"
                !hasTopTop && !hasTopLeft && hasTopRight && !hasMiddle && !hasBottomLeft && hasBottomRight && !hasBottomBottom -> "1"
                hasTopTop && !hasTopLeft && hasTopRight && hasMiddle && hasBottomLeft && !hasBottomRight && hasBottomBottom -> "2"
                hasTopTop && !hasTopLeft && hasTopRight && hasMiddle && !hasBottomLeft && hasBottomRight && hasBottomBottom -> "3"
                !hasTopTop && hasTopLeft && hasTopRight && hasMiddle && !hasBottomLeft && hasBottomRight && !hasBottomBottom -> "4"
                hasTopTop && hasTopLeft && !hasTopRight && hasMiddle && !hasBottomLeft && hasBottomRight && hasBottomBottom -> "5"
                hasTopTop && hasTopLeft && !hasTopRight && hasMiddle && hasBottomLeft && hasBottomRight && hasBottomBottom -> "6"
                hasTopTop && !hasTopLeft && hasTopRight && !hasMiddle && !hasBottomLeft && hasBottomRight && !hasBottomBottom -> "7"
                hasTopTop && hasTopLeft && hasTopRight && hasMiddle && hasBottomLeft && hasBottomRight && hasBottomBottom -> "8"
                hasTopTop && hasTopLeft && hasTopRight && hasMiddle && !hasBottomLeft && hasBottomRight && hasBottomBottom -> "9"
                else -> throw IllegalStateException()
            }

        }.forEach { outputValueString += it }

        return outputValueString.toInt()
    }
}