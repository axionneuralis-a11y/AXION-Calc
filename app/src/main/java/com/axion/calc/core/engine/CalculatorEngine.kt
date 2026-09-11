package com.axion.calc.core.engine

object CalculatorEngine {
    fun evaluate(expression: String): String {
        if (expression.isBlank()) return "0"
        return try {
            ExpressionParser.formatNumber(ExpressionParser(expression).parse())
        } catch (_: Throwable) {
            "Error"
        }
    }
}
