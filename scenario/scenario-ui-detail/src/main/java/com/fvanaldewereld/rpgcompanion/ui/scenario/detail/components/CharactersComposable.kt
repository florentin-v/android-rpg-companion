package com.fvanaldewereld.rpgcompanion.ui.scenario.detail.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.CharactersModel
import com.fvanaldewereld.rpgcompanion.common.ui.theme.Typography

@Composable
internal fun CharactersComposable(charactersModel: CharactersModel) {
    Text(
        "Characters",
        style = Typography.headlineSmall,
        modifier = Modifier.padding(top = 10.dp, bottom = 5.dp),
    )
    charactersModel.characters?.forEach { character ->
        Text(
            character.name ?: "/",
            style = Typography.titleLarge,
            modifier = Modifier.padding(top = 10.dp, bottom = 5.dp),
        )
        character.description?.paragraphs?.forEach { paragraph ->
            Text(
                paragraph,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
            )
        }
    }
}
