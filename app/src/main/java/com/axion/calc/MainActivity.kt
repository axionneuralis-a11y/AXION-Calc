package com.axion.calc

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.axion.calc.data.preferences.SettingsDataStore
import com.axion.calc.ui.navigation.NavGraph
import com.axion.calc.ui.theme.AxionTheme

class MainActivity : ComponentActivity() {
    private val settings by lazy { SettingsDataStore(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val prefs by settings.prefs.collectAsStateWithLifecycle(initialValue = com.axion.calc.data.preferences.UserPrefs())
            AxionTheme(prefs.theme) { NavGraph(this) }
        }
    }
}
