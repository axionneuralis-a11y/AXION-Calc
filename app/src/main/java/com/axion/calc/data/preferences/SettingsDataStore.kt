package com.axion.calc.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.settingsDataStore by preferencesDataStore("axion_settings")

class SettingsDataStore(private val context: Context) {
    private object Keys {
        val theme = stringPreferencesKey("theme")
        val language = stringPreferencesKey("language")
    }

    val prefs: Flow<UserPrefs> = context.settingsDataStore.data.map { p ->
        UserPrefs(
            theme = p[Keys.theme]?.let { runCatching { ThemePreference.valueOf(it) }.getOrNull() }
                ?: ThemePreference.FOLLOW_SYSTEM,
            language = p[Keys.language]?.let { runCatching { LanguagePreference.valueOf(it) }.getOrNull() }
                ?: LanguagePreference.FOLLOW_PHONE,
        )
    }

    suspend fun setTheme(theme: ThemePreference) = context.settingsDataStore.edit { it[Keys.theme] = theme.name }
    suspend fun setLanguage(language: LanguagePreference) = context.settingsDataStore.edit { it[Keys.language] = language.name }
}
