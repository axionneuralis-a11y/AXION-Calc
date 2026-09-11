package com.axion.calc.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.axion.calc.data.preferences.ThemePreference

private val LightColors = lightColorScheme(primary = AxionBlue)
private val DarkColors = darkColorScheme(primary = AxionBlueDark)

@Composable
fun AxionTheme(preference: ThemePreference, content: @Composable () -> Unit) {
    val systemDark = isSystemInDarkTheme()
    val dark = when (preference) {
        ThemePreference.FOLLOW_SYSTEM -> systemDark
        ThemePreference.LIGHT -> false
        ThemePreference.DARK -> true
    }
    val scheme = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && dark -> dynamicDarkColorScheme(LocalContext.current)
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> dynamicLightColorScheme(LocalContext.current)
        dark -> DarkColors
        else -> LightColors
    }
    MaterialTheme(colorScheme = scheme, typography = AxionTypography, content = content)
}
