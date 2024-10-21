package com.fvanaldewereld.rpgcompanion.ui.scenario.detail.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.AuthorModel
import com.fvanaldewereld.rpgcompanion.common.ui.theme.Typography
import com.fvanaldewereld.rpgcompanion.ui.scenario.detail.R

@Composable
internal fun AuthorComposable(authorModel: AuthorModel) {
    Text(
        text = authorModel.name?.let { stringResource(R.string.scenarioDetail_page_author, it) }
            ?: stringResource(id = R.string.scenarioDetail_page_noAuthor),
        style = Typography.titleLarge.copy(
            fontStyle = FontStyle.Italic,
            color = Color.LightGray,
        ),
    )
}
