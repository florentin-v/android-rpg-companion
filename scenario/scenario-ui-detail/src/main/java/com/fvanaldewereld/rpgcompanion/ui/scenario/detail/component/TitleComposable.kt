package com.fvanaldewereld.rpgcompanion.ui.scenario.detail.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.TitleModel
import com.fvanaldewereld.rpgcompanion.common.ui.theme.Typography
import com.fvanaldewereld.rpgcompanion.ui.scenario.detail.R

@Composable
internal fun TitleComposable(titleModel: TitleModel) {
    Text(
        text = titleModel.value ?: stringResource(id = R.string.scenarioDetail_page_noTitle),
        style = Typography.headlineMedium,
        textAlign = TextAlign.Center,
    )
}
