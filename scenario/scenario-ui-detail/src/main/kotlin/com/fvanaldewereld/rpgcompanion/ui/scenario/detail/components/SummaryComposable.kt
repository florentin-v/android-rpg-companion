package com.fvanaldewereld.rpgcompanion.ui.scenario.detail.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.SummaryModel
import com.fvanaldewereld.rpgcompanion.common.ui.theme.Typography

@Composable
internal fun SummaryComposable(summaryModel: SummaryModel) {
    Text(
        "Summary",
        style = Typography.headlineSmall,
        modifier = Modifier.padding(top = 10.dp, bottom = 5.dp),
    )
    summaryModel.text?.paragraphs?.forEach { paragraph ->
        Text(
            paragraph,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
        )
    }
}
