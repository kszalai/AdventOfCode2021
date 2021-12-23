package day10

import common.readInputFile
import java.lang.IllegalArgumentException
import java.util.*

/**
 * ) - 3
 * ] - 57
 * } - 1197
 * > - 25137
 */
fun main() {
    val input = readInputFile<String>("src/main/kotlin/day10/input.txt")

    val corrupted = input.map { row ->
        val stack = Stack<Char>()
        var lastChar: Char? = null
        try {
            row.forEachIndexed { i, c ->
                when (c) {
                    '(' -> stack.push(c)
                    ')' -> {
                        if (stack.peek() == '(')
                            stack.pop()
                        else if (stack.peek() != '(') {
                            lastChar = c
                            throw IllegalStateException()
                        }
                        else if (i != row.length - 1) {
                            lastChar = c
                            return@forEachIndexed
                        }
                    }
                    '[' -> stack.push(c)
                    ']' -> {
                        if (stack.peek() == '[')
                            stack.pop()
                        else if (stack.peek() != '[') {
                            lastChar = c
                            throw IllegalStateException()
                        }
                        else if (i != row.length - 1) {
                            lastChar = c
                            return@forEachIndexed
                        }
                    }
                    '{' -> stack.push(c)
                    '}' -> {
                        if (stack.peek() == '{')
                            stack.pop()
                        else if (stack.peek() != '{') {
                            lastChar = c
                            throw IllegalStateException()
                        }
                        else if (i != row.length - 1) {
                            lastChar = c
                            return@forEachIndexed
                        }
                    }
                    '<' -> stack.push(c)
                    '>' -> {
                        if (stack.peek() == '<')
                            stack.pop()
                        else if (stack.peek() != '<') {
                            lastChar = c
                            throw IllegalStateException()
                        }
                        else if (i != row.length - 1) {
                            lastChar = c
                            return@forEachIndexed
                        }
                    }
                }
            }
            null
        } catch (e: IllegalStateException) {
            lastChar
        }
    }.filterNotNull().map { c ->
        when (c) {
            ')' -> 3
            ']' -> 57
            '}' -> 1197
            '>' -> 25137
            else -> throw IllegalArgumentException()
        }
    }.sum()

    println(corrupted)
}