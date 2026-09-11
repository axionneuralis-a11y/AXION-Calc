package com.axion.calc.core.engine

import java.util.Locale

class ExpressionParser(private val input: String) {
    private var index = 0

    fun parse(): Double {
        val value = parseExpression()
        skipWhitespace()
        if (index != input.length) throw IllegalArgumentException("Unexpected token at $index")
        return value
    }

    private fun parseExpression(): Double {
        var value = parseTerm()
        while (true) {
            skipWhitespace()
            when (peek()) {
                '+' -> { index++; value += parseTerm() }
                '-' -> { index++; value -= parseTerm() }
                else -> return value
            }
        }
    }

    private fun parseTerm(): Double {
        var value = parseUnary()
        while (true) {
            skipWhitespace()
            when (peek()) {
                '*' -> { index++; value *= parseUnary() }
                '/', '%' -> {
                    val operator = peek()
                    index++
                    val divisor = parseUnary()
                    if (divisor == 0.0) throw ArithmeticException("Division by zero")
                    value = if (operator == '/') value / divisor else value % divisor
                }
                else -> return value
            }
        }
    }

    private fun parseUnary(): Double {
        skipWhitespace()
        return when (peek()) {
            '+' -> { index++; parseUnary() }
            '-' -> { index++; -parseUnary() }
            else -> parsePrimary()
        }
    }

    private fun parsePrimary(): Double {
        skipWhitespace()
        if (peek() == '(') {
            index++
            val value = parseExpression()
            skipWhitespace()
            if (peek() != ')') throw IllegalArgumentException("Missing closing parenthesis")
            index++
            return value
        }
        val start = index
        var hasDigits = false
        var hasDot = false
        while (index < input.length) {
            val c = input[index]
            when {
                c.isDigit() -> { hasDigits = true; index++ }
                c == '.' && !hasDot -> { hasDot = true; index++ }
                else -> break
            }
        }
        if (!hasDigits) throw IllegalArgumentException("Expected number at $start")
        return input.substring(start, index).toDoubleOrNull()
            ?: throw IllegalArgumentException("Invalid number")
    }

    private fun skipWhitespace() { while (index < input.length && input[index].isWhitespace()) index++ }
    private fun peek(): Char = if (index < input.length) input[index] else '\u0000'

    companion object {
        fun formatNumber(value: Double): String {
            if (!value.isFinite()) return "Error"
            val rounded = if (kotlin.math.abs(value - value.toLong()) < 1e-10) value.toLong().toDouble() else value
            return String.format(Locale.US, "%.10f", rounded).trimEnd('0').trimEnd('.')
        }
    }
}
