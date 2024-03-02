package com.fvanaldewereld.rpgcompanion.ui.scenario.detail.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ScenarioModel
import com.fvanaldewereld.rpgcompanion.common.ui.components.RpgCompanionTopAppBar
import com.fvanaldewereld.rpgcompanion.common.ui.theme.RpgCompanionTheme
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory

@Composable
internal fun ScenarioDetailSuccess(
    scenario: ScenarioModel,
    onBackButtonPressed: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            RpgCompanionTopAppBar(
                title = "${scenario.id} // Scenario Detail ${scenario.title?.title?.let { "- $it" }}",
                onBackButtonPressed = onBackButtonPressed,
            )
        },
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                scenario.title?.let { title ->
                    TitleComposable(title)
                }
            }

            item {
                scenario.author?.let { author ->
                    AuthorComposable(author)
                }
            }

            item {
                scenario.information?.nbPlayers?.let { nbPlayers ->
                    NumberOfPlayersComposable(nbPlayers)
                }
            }

            item {
                scenario.information?.genres?.let { genres ->
                    GenresComposable(genres)
                }
            }

            item {
                scenario.information?.themes?.let { themes ->
                    ThemesComposable(themes)
                }
            }

            item {
                scenario.summary?.let { summary ->
                    SummaryComposable(summary)
                }
            }

            item {
                scenario.places?.let { places ->
                    PlacesComposable(places)
                }
            }

            item {
                scenario.characters?.let { characters ->
                    CharactersComposable(characters)
                }
            }

            item {
                scenario.chapters?.let { chaptersModel ->
                    ChaptersComposable(chaptersModel)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun ScenarioDetailSuccessPreview() {
    RpgCompanionTheme {
        ScenarioDetailSuccess(scenario = ScenarioModelMockFactory.scenarioModelWithoutId)
    }
}
