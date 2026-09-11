package com.axion.calc.ui.calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val keys = listOf(
    listOf("AC", "(", ")", "÷"),
    listOf("7", "8", "9", "×"),
    listOf("4", "5", "6", "−"),
    listOf("1", "2", "3", "+"),
    listOf("0", ".", "%", "=")
)

@Composable
fun KeypadComponent(
    onKey: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth().padding(12.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        keys.forEach { row ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                row.forEach { key ->
                    val isEqual = key == "="
                    val isClear = key == "AC"
                    val isOperator = key in setOf("÷", "×", "−", "+", "%", "(", ")")
                    val colors = when {
                        isEqual -> ButtonDefaults.buttonColors()
                        isClear -> ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error)
                        else -> ButtonDefaults.buttonColors(
                            containerColor = if (isOperator) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.surfaceVariant,
                            contentColor = if (isOperator) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                    val buttonModifier = Modifier.weight(1f).height(64.dp)
                    if (isClear) {
                        OutlinedButton(onClick = { onKey(key) }, modifier = buttonModifier, colors = colors) { Text(key, fontSize = 20.sp) }
                    } else {
                        Button(onClick = { onKey(key) }, modifier = buttonModifier, colors = colors) { Text(key, fontSize = 22.sp) }
                    }
                }
            }
        }
    }
}
