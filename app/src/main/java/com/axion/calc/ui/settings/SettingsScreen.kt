package com.axion.calc.ui.settings

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.axion.calc.R
import com.axion.calc.core.role.DefaultCalculatorHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    activity: Activity,
    onBack: () -> Unit,
    onPrivacy: () -> Unit,
    vm: SettingsViewModel = viewModel(),
) {
    val prefs by vm.prefs.collectAsState()
    val isDefault = DefaultCalculatorHelper.isDefault(activity)

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(stringResource(R.string.settings)) },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = stringResource(R.string.back))
                }
            },
        )
    }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 12.dp),
        ) {
            LanguageSection(prefs.language, vm::setLanguage)
            HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
            ThemeSection(prefs.theme, vm::setTheme)
            HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
            DefaultAppSection(
                DefaultCalculatorHelper.isSupported(),
                isDefault,
            ) { DefaultCalculatorHelper.requestRole(activity) }
            HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
            PrivacyPolicyItem(onClick = onPrivacy)
            FeedbackItem()
            HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
            PromoBanners()
        }
    }
}
