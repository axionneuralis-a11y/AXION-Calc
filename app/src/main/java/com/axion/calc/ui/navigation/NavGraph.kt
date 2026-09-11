package com.axion.calc.ui.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.axion.calc.ui.calculator.CalculatorScreen
import com.axion.calc.ui.settings.PrivacyPolicyScreen
import com.axion.calc.ui.settings.SettingsScreen

private enum class Destination { CALCULATOR, SETTINGS, PRIVACY }

@Composable
fun NavGraph(activity: Activity) {
    var destination by rememberSaveable { mutableStateOf(Destination.CALCULATOR) }
    when (destination) {
        Destination.CALCULATOR -> CalculatorScreen(onSettings = { destination = Destination.SETTINGS })
        Destination.SETTINGS -> SettingsScreen(
            activity = activity,
            onBack = { destination = Destination.CALCULATOR },
            onPrivacy = { destination = Destination.PRIVACY },
        )
        Destination.PRIVACY -> PrivacyPolicyScreen(onBack = { destination = Destination.SETTINGS })
    }
}
