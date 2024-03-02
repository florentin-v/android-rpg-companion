package com.fvanaldewereld.rpgcompanion.ui.scenario.detail.components

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
    authorModel.name?.let { authorName ->
        Text(
            text = stringResource(R.string.scenario_detail_page_author, authorName),
            style = Typography.titleLarge.copy(
                fontStyle = FontStyle.Italic,
                color = Color.LightGray,
            ),
        )
    }
}
