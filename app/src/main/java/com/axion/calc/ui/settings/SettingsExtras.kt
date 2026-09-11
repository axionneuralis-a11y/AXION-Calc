package com.axion.calc.ui.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.axion.calc.R

@Composable
fun PrivacyPolicyItem(onClick: () -> Unit) {
    ListItem(
        headlineContent = { Text(stringResource(R.string.privacy_policy)) },
        leadingContent = {
            Icon(Icons.Default.Info, contentDescription = null)
        },
        modifier = Modifier.clickable(onClick = onClick),
    )
}

@Composable
fun FeedbackItem() {
    val context = LocalContext.current
    ListItem(
        headlineContent = { Text(stringResource(R.string.feedback)) },
        leadingContent = {
            Icon(Icons.Default.Email, contentDescription = null)
        },
        modifier = Modifier.clickable {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf("axionneuralis@gmail.com", "azrielpace852@gmail.com"))
                putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.feedback_subject))
            }
            try {
                context.startActivity(Intent.createChooser(intent, context.getString(R.string.feedback_chooser)))
            } catch (_: Exception) {
                // No email client available
            }
        },
    )
}

@Composable
fun PromoBanners() {
    val context = LocalContext.current

    Column {
        BannerCard(
            text = stringResource(R.string.banner_github),
            icon = { Icon(Icons.Default.Star, contentDescription = null) },
        ) {
            openUrl(context, "https://github.com/axionneuralis-a11y/AXION-Calc/")
        }
        BannerCard(
            text = stringResource(R.string.banner_romance),
        ) {
            openUrl(context, "https://romance-engine.pages.dev/")
        }
        BannerCard(
            text = stringResource(R.string.banner_follow),
        ) {
            openUrl(context, "https://axion-neuralis.axn.cc.cd")
        }
    }
}

@Composable
private fun BannerCard(
    text: String,
    icon: @Composable (() -> Unit)? = null,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
    ) {
        ListItem(
            headlineContent = {
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium,
                )
            },
            leadingContent = icon,
        )
    }
}

private fun openUrl(context: android.content.Context, url: String) {
    try {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    } catch (_: Exception) {
        // No browser available
    }
}
