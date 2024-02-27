package com.fvanaldewereld.rpgcompanion.ui.scenario.detail.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
internal fun NumberOfPlayersComposable(nbPlayers: Int) {
    AssistChip(
        onClick = {},
        modifier = Modifier.padding(2.dp),
        colors = AssistChipDefaults.assistChipColors(Color.Green.copy(alpha = 0.5F)),
        leadingIcon = {
            Icon(imageVector = Icons.Default.Person, contentDescription = null)
        },
        label = {
            Text(
                nbPlayers.toString(),
                textAlign = TextAlign.Center,
            )
        },
    )
}
