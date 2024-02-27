package com.fvanaldewereld.rpgcompanion.ui.scenario.detail.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.TitleModel
import com.fvanaldewereld.rpgcompanion.common.ui.theme.Typography

@Composable
internal fun TitleComposable(titleModel: TitleModel) {
    titleModel.title?.let { titleText ->
        Text(
            text = titleText,
            style = Typography.headlineMedium,
            textAlign = TextAlign.Center,
        )
    }
}
