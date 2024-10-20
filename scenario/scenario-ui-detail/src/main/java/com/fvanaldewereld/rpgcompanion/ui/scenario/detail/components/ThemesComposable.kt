package com.fvanaldewereld.rpgcompanion.ui.scenario.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ThemeListModel

@Composable
@OptIn(ExperimentalLayoutApi::class)
internal fun ThemesComposable(themeListModel: ThemeListModel) {
    FlowRow(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(horizontal = 15.dp),
    ) {
        themeListModel.values.forEach { theme ->
            AssistChip(
                onClick = {},
                modifier = Modifier.padding(2.dp),
                colors = AssistChipDefaults.assistChipColors(Color.Blue.copy(alpha = 0.5F)),
                label = {
                    Text(
                        theme,
                        textAlign = TextAlign.Center,
                    )
                },
            )
        }
    }
}
