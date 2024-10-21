package com.fvanaldewereld.rpgcompanion.ui.scenario.detail.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.MainInfoModel

@Composable
fun ScenarioDetailMainInfo(mainInfoModel: MainInfoModel, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            TitleComposable(mainInfoModel.title)
        }

        item {
            AuthorComposable(mainInfoModel.author)
        }

        item {
            NumberOfPlayersComposable(mainInfoModel.information.nbPlayers)
        }

        item {
            GenresComposable(mainInfoModel.information.genreList)
        }

        item {
            ThemesComposable(mainInfoModel.information.themeList)
        }

        item {
            SummaryComposable(mainInfoModel.summary)
        }
    }
}
