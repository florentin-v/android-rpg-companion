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
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.GenreListModel

@Composable
@OptIn(ExperimentalLayoutApi::class)
internal fun GenresComposable(genreListModel: GenreListModel) {
    FlowRow(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(horizontal = 15.dp),
    ) {
        genreListModel.values.forEach { genre ->
            AssistChip(
                onClick = {},
                modifier = Modifier.padding(2.dp),
                colors = AssistChipDefaults.assistChipColors(Color.Red.copy(alpha = 0.5F)),
                label = {
                    Text(
                        genre,
                        textAlign = TextAlign.Center,
                    )
                },
            )
        }
    }
}
