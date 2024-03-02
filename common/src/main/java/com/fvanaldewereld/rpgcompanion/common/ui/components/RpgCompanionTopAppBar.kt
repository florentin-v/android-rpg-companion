package com.fvanaldewereld.rpgcompanion.common.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fvanaldewereld.rpgcompanion.common.ui.theme.RpgCompanionTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RpgCompanionTopAppBar(
    modifier: Modifier = Modifier,
    title: String = "",
    onBackButtonPressed: (() -> Unit)? = null,
) {
    TopAppBar(
        navigationIcon = {
            onBackButtonPressed?.let {
                IconButton(
                    onClick = it,
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back button")
                }
            }
        },
        title = { Text(title) },
        modifier = modifier,
    )
}

@Preview
@Composable
private fun RpgCompanionTopBarPreview() {
    RpgCompanionTheme {
        RpgCompanionTopAppBar(
            title = "Title",
            onBackButtonPressed = {},
        )
    }
}
