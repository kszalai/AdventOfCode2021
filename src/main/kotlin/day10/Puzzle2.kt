package day10

import common.readInputFile
import java.util.*
import kotlin.math.floor

fun main() {
    val input = readInputFile<String>("src/main/kotlin/day10/input.txt")

    val incomplete = input.map { row ->
        val stack = Stack<Char>()
        try {
            row.forEachIndexed { i, c ->
                when (c) {
                    '(' -> stack.push(c)
                    ')' -> {
                        if (stack.peek() == '(') stack.pop()
                        else if (stack.peek() != '(') throw IllegalStateException()
                        else if (i != row.length - 1) return@forEachIndexed
                    }
                    '[' -> stack.push(c)
                    ']' -> {
                        if (stack.peek() == '[') stack.pop()
                        else if (stack.peek() != '[') throw IllegalStateException()
                        else if (i != row.length - 1) return@forEachIndexed
                    }
                    '{' -> stack.push(c)
                    '}' -> {
                        if (stack.peek() == '{') stack.pop()
                        else if (stack.peek() != '{') throw IllegalStateException()
                        else if (i != row.length - 1) return@forEachIndexed
                    }
                    '<' -> stack.push(c)
                    '>' -> {
                        if (stack.peek() == '<') stack.pop()
                        else if (stack.peek() != '<') throw IllegalStateException()
                        else if (i != row.length - 1) return@forEachIndexed
                    }
                }
            }
            stack
        } catch (e: IllegalStateException) {
            null
        }
    }.filterNotNull().map { stack ->
        var total = 0.0
        while (stack.isNotEmpty()) {
            total *= 5.0
            total += when (stack.pop()) {
                '(' -> 1.0
                '[' -> 2.0
                '{' -> 3.0
                '<' -> 4.0
                else -> throw IllegalArgumentException()
            }
        }
        total
    }.sorted()

    val middle = floor((incomplete.size.toDouble() / 2.0)).toInt()

    println(incomplete[middle].toBigDecimal())
}