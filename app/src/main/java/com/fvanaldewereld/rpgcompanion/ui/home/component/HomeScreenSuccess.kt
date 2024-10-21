package com.fvanaldewereld.rpgcompanion.ui.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fvanaldewereld.rpgcompanion.ui.home.component.lastCharacter.LastCharacterList
import com.fvanaldewereld.rpgcompanion.ui.home.component.lastGame.LastGameList
import com.fvanaldewereld.rpgcompanion.ui.home.component.lastScenario.LastScenarioList
import com.fvanaldewereld.rpgcompanion.ui.home.component.lastSession.LastSessionList
import com.fvanaldewereld.rpgcompanion.ui.home.model.HomeScreenAction
import com.fvanaldewereld.rpgcompanion.ui.home.model.LastScenarioUI
import com.fvanaldewereld.rpgcompanion.ui.home.state.HomeUIState
import com.fvanaldewereld.rpgcompanion.ui.home.viewModel.HomeViewModel.Companion.TmpMock
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList

@Composable
internal fun HomeScreenSuccess(
    state: HomeUIState.Success,
    onHomeScreenAction: (HomeScreenAction) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        item {
            LastGameList(
                lastGameModelList = state.lastGameModelList.toPersistentList(),
                onHomeScreenAction = onHomeScreenAction,
            )
        }
        item {
            LastSessionList(
                lastSessionModelList = state.lastSessionModelList.toPersistentList(),
                onHomeScreenAction = onHomeScreenAction,
            )
        }
        item {
            LastCharacterList(
                lastCharacterUIList = state.lastCharacterUIList.toPersistentList(),
                onHomeScreenAction = onHomeScreenAction,
            )
        }
        item {
            LastScenarioList(
                lastScenarioUIList = state.lastScenarioUIList.toPersistentList(),
                onHomeScreenAction = onHomeScreenAction,
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenSuccessPreview() {
    HomeScreenSuccess(
        HomeUIState.Success(
            lastCharacterUIList = TmpMock.lastCharacterUIList,
            lastGameModelList = TmpMock.lastGameModelList,
            lastSessionModelList = TmpMock.lastSessionModelList,
            lastScenarioUIList = persistentListOf(
                LastScenarioUI(
                    id = 1L,
                    title = "title",
                    author = "author",
                ),
            ),
        ),
        onHomeScreenAction = {},
    )
}
