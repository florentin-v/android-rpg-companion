package com.fvanaldewereld.rpgcompanion.ui.scenario.detail.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.CharactersModel
import com.fvanaldewereld.rpgcompanion.common.ui.theme.Typography
import com.fvanaldewereld.rpgcompanion.ui.scenario.detail.R

@Composable
fun ScenarioDetailCharacters(charactersModel: CharactersModel, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Text(
                "Characters",
                style = Typography.headlineSmall,
                modifier = Modifier.padding(top = 10.dp, bottom = 5.dp),
            )
            with(charactersModel.characters) {
                if (isNotEmpty()) {
                    forEach { character ->
                        Text(
                            character.name,
                            style = Typography.titleLarge,
                            modifier = Modifier.padding(top = 10.dp, bottom = 5.dp),
                        )
                        character.description.paragraphs.forEach { paragraph ->
                            Text(
                                paragraph,
                                textAlign = TextAlign.Justify,
                                modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
                            )
                        }
                    }
                } else {
                    Text(
                        stringResource(id = R.string.scenarioDetail_page_no_characters),
                        style = Typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 10.dp, bottom = 5.dp),
                    )
                }
            }
        }
    }
}
