package com.fvanaldewereld.rpgcompanion.ui.scenario.list.components

import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.fvanaldewereld.rpgcompanion.common.ui.theme.RpgCompanionTheme

@Composable
internal fun ScenarioListItemChips(text: String) {
    ElevatedSuggestionChip(
        onClick = { },
        label = {
            Text(text, textAlign = TextAlign.Center)
        },
    )
}

@Preview
@Composable
private fun ScenarioListItemChipsPreview() {
    RpgCompanionTheme {
        ScenarioListItemChips(text = "Horror")
    }
}
