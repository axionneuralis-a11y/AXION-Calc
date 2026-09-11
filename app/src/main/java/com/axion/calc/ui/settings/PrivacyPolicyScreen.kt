package com.axion.calc.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.axion.calc.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacyPolicyScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.privacy_policy_title)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = stringResource(R.string.back))
                    }
                },
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 12.dp),
        ) {
            Text(
                text = stringResource(R.string.privacy_intro),
                style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(Modifier.height(16.dp))

            Section(stringResource(R.string.privacy_section_data), stringResource(R.string.privacy_data_body))
            Section(stringResource(R.string.privacy_section_storage), stringResource(R.string.privacy_storage_body))
            Section(stringResource(R.string.privacy_section_network), stringResource(R.string.privacy_network_body))
            Section(stringResource(R.string.privacy_section_permissions), stringResource(R.string.privacy_permissions_body))
            Section(stringResource(R.string.privacy_section_third_party), stringResource(R.string.privacy_third_party_body))
            Section(stringResource(R.string.privacy_section_children), stringResource(R.string.privacy_children_body))
            Section(stringResource(R.string.privacy_section_changes), stringResource(R.string.privacy_changes_body))
            Section(stringResource(R.string.privacy_section_contact), stringResource(R.string.privacy_contact_body))

            Spacer(Modifier.height(12.dp))
            Text(
                text = stringResource(R.string.privacy_last_updated),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
private fun Section(title: String, body: String) {
    Text(text = title, style = MaterialTheme.typography.titleMedium)
    Spacer(Modifier.height(4.dp))
    Text(text = body, style = MaterialTheme.typography.bodyMedium)
    Spacer(Modifier.height(16.dp))
}
