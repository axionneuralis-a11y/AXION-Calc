package com.axion.calc.ui.calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.weight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.axion.calc.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorScreen(onSettings: () -> Unit, vm: CalculatorViewModel = viewModel()) {
    val expression by vm.expression.collectAsState()
    val result by vm.result.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                actions = {
                    IconButton(onClick = vm::backspace) { Icon(Icons.Default.Backspace, contentDescription = stringResource(R.string.backspace)) }
                    IconButton(onClick = onSettings) { Icon(Icons.Default.Settings, contentDescription = stringResource(R.string.settings)) }
                },
            )
        },
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
            DisplayComponent(expression, result, modifier = Modifier.weight(1f))
            KeypadComponent(onKey = { key ->
                when (key) {
                    "AC" -> vm.clear()
                    "=" -> vm.equals()
                    "−" -> vm.input("-")
                    else -> vm.input(key)
                }
            })
        }
    }
}
