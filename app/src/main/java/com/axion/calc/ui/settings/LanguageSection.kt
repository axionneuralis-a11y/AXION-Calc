package com.axion.calc.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.axion.calc.R
import com.axion.calc.data.preferences.LanguagePreference

@Composable
fun LanguageSection(selected: LanguagePreference, onSelect: (LanguagePreference) -> Unit) {
    Column {
        Text(stringResource(R.string.language), style = MaterialTheme.typography.titleMedium)
        listOf(
            LanguagePreference.FOLLOW_PHONE to stringResource(R.string.language_follow_phone),
            LanguagePreference.ENGLISH to stringResource(R.string.language_english),
            LanguagePreference.INDONESIAN to stringResource(R.string.language_indonesian),
        ).forEach { (value, label) ->
            ListItem(
                headlineContent = { Text(label) },
                leadingContent = { RadioButton(selected = selected == value, onClick = { onSelect(value) }) },
            )
        }
    }
}
