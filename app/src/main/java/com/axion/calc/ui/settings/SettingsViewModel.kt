package com.axion.calc.ui.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.axion.calc.core.locale.LocaleHelper
import com.axion.calc.data.preferences.LanguagePreference
import com.axion.calc.data.preferences.SettingsDataStore
import com.axion.calc.data.preferences.ThemePreference
import com.axion.calc.data.preferences.UserPrefs
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application) : AndroidViewModel(application) {
    private val store = SettingsDataStore(application)
    val prefs: StateFlow<UserPrefs> = store.prefs.stateIn(viewModelScope, SharingStarted.Eagerly, UserPrefs())

    fun setTheme(theme: ThemePreference) = viewModelScope.launch { store.setTheme(theme) }

    fun setLanguage(language: LanguagePreference) = viewModelScope.launch {
        store.setLanguage(language)
        LocaleHelper.apply(language)
    }
}
