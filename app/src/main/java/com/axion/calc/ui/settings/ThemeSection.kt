package com.axion.calc.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.axion.calc.R
import com.axion.calc.data.preferences.ThemePreference

@Composable
fun ThemeSection(selected: ThemePreference, onSelect: (ThemePreference) -> Unit) {
    Column {
        Text(stringResource(R.string.theme), style = MaterialTheme.typography.titleMedium)
        listOf(
            ThemePreference.FOLLOW_SYSTEM to stringResource(R.string.theme_follow_system),
            ThemePreference.LIGHT to stringResource(R.string.theme_light),
            ThemePreference.DARK to stringResource(R.string.theme_dark),
        ).forEach { (value, label) ->
            ListItem(
                headlineContent = { Text(label) },
                leadingContent = { RadioButton(selected = selected == value, onClick = { onSelect(value) }) },
            )
        }
    }
}
