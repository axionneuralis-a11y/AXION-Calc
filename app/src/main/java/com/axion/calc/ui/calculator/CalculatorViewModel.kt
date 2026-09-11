package com.axion.calc.ui.calculator

import androidx.lifecycle.ViewModel
import com.axion.calc.core.engine.CalculatorEngine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CalculatorViewModel : ViewModel() {
    private val _expression = MutableStateFlow("")
    val expression: StateFlow<String> = _expression.asStateFlow()

    private val _result = MutableStateFlow("0")
    val result: StateFlow<String> = _result.asStateFlow()

    private var justEvaluated = false

    fun input(value: String) {
        if (justEvaluated && value.firstOrNull()?.isDigit() == true) {
            _expression.value = ""
        }
        justEvaluated = false
        _expression.value += value
        _result.value = preview()
    }

    fun clear() {
        _expression.value = ""
        _result.value = "0"
        justEvaluated = false
    }

    fun backspace() {
        if (_expression.value.isNotEmpty()) _expression.value = _expression.value.dropLast(1)
        _result.value = preview()
        justEvaluated = false
    }

    fun equals() {
        if (_expression.value.isBlank()) return
        _result.value = CalculatorEngine.evaluate(_expression.value.replace('×','*').replace('÷','/'))
        justEvaluated = true
    }

    private fun preview(): String {
        val expr = _expression.value.replace('×','*').replace('÷','/')
        if (expr.isBlank()) return "0"
        if (expr.any { it in "+*/(" || it == ')' } && expr.lastOrNull() in charArrayOf('+','-','*','/','%','(')) return _result.value
        val value = CalculatorEngine.evaluate(expr)
        return if (value == "Error") _result.value else value
    }
}
