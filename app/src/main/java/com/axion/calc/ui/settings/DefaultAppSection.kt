package com.axion.calc.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.axion.calc.R

@Composable
fun DefaultAppSection(supported: Boolean, isDefault: Boolean, onSetDefault: () -> Unit) {
    Text(stringResource(R.string.default_calculator), style = MaterialTheme.typography.titleMedium)
    ListItem(
        headlineContent = {
            Text(if (isDefault) stringResource(R.string.is_default) else stringResource(R.string.default_calculator))
        },
        supportingContent = if (!supported) {
            { Text(stringResource(R.string.not_supported)) }
        } else {
            { Text(stringResource(R.string.default_calculator_description)) }
        },
    )
    if (supported && !isDefault) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Button(onClick = onSetDefault) { Text(stringResource(R.string.set_default)) }
        }
    }
}
