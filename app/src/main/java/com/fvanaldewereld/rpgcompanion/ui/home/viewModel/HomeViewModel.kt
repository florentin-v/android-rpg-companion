package com.fvanaldewereld.rpgcompanion.ui.home.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fvanaldewereld.rpgcompanion.api.domain.character.model.CharacterModel
import com.fvanaldewereld.rpgcompanion.api.domain.game.model.GameModel
import com.fvanaldewereld.rpgcompanion.api.domain.session.model.SessionModel
import com.fvanaldewereld.rpgcompanion.api.domain.session.model.SessionStatus
import com.fvanaldewereld.rpgcompanion.common.dispatchers.KDispatchers
import com.fvanaldewereld.rpgcompanion.ui.home.model.CharacterUI
import com.fvanaldewereld.rpgcompanion.ui.home.state.HomeUIState
import com.fvanaldewereld.rpgcompanion.ui.home.state.LastCharacterListState
import com.fvanaldewereld.rpgcompanion.ui.home.state.LastGameListState
import com.fvanaldewereld.rpgcompanion.ui.home.state.LastScenarioListState
import com.fvanaldewereld.rpgcompanion.ui.home.state.LastSessionListState
import com.fvanaldewereld.rpgcompanion.ui.home.viewModel.slice.GetLastCharacterListSlice
import com.fvanaldewereld.rpgcompanion.ui.home.viewModel.slice.GetLastGameListSlice
import com.fvanaldewereld.rpgcompanion.ui.home.viewModel.slice.GetLastScenarioListSlice
import com.fvanaldewereld.rpgcompanion.ui.home.viewModel.slice.GetLastSessionListSlice
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val dispatchers: KDispatchers,
    private val getLastScenarioListSlice: GetLastScenarioListSlice,
    private val getLastGameListSlice: GetLastGameListSlice,
    private val getLastSessionListSlice: GetLastSessionListSlice,
    private val getLastCharacterListSlice: GetLastCharacterListSlice,
) : ViewModel(),
    GetLastScenarioListSlice by getLastScenarioListSlice,
    GetLastGameListSlice by getLastGameListSlice,
    GetLastSessionListSlice by getLastSessionListSlice,
    GetLastCharacterListSlice by getLastCharacterListSlice {

    var homeUIStateFlow: StateFlow<HomeUIState> = savedStateHandle.getStateFlow<HomeUIState>(
        HOME_UI_STATE_KEY,
        HomeUIState.Loading,
    )

    private val combinedFlow = combine(
        lastCharacterListStateFlow,
        lastGameListStateFlow,
        lastSessionListStateFlow,
        lastScenarioListStateFlow,
    ) { lastCharacterListState, lastGameListState, lastSessionListState, lastScenarioListState ->
        val lastCharacterListStateSuccess = lastCharacterListState as? LastCharacterListState.Success
        val lastGameListStateSuccess = lastGameListState as? LastGameListState.Success
        val lastSessionListStateSuccess = lastSessionListState as? LastSessionListState.Success
        val lastScenarioListStateSuccess = lastScenarioListState as? LastScenarioListState.Success

        HomeUIState.Success(
            lastCharacterUIList = lastCharacterListStateSuccess?.lastCharacterList.orEmpty(),
            lastGameModelList = lastGameListStateSuccess?.lastGameList.orEmpty(),
            lastSessionModelList = lastSessionListStateSuccess?.lastSessionList.orEmpty(),
            lastScenarioUIList = lastScenarioListStateSuccess?.lastScenarioList.orEmpty(),
        )
    }

    init {
        if (homeUIStateFlow.value is HomeUIState.Loading) {
            viewModelScope.launch {
                delay(5_000L)
                combinedFlow.collect { homeUiState ->
                    savedStateHandle[HOME_UI_STATE_KEY] = homeUiState
                }
            }

            getData()
        }
    }

    private fun getData() {
        viewModelScope.launch {
            withContext(dispatchers.default()) {
                getLastCharacterListSlice.getLastCharacterList(number = 2)
                getLastGameListSlice.getLastGameList(number = 2)
                getLastScenarioListSlice.getLastScenarioList(number = 2)
                getLastSessionListSlice.getLastSessionList(number = 2)
            }
        }
    }

    companion object {
        const val HOME_UI_STATE_KEY = "HOME_UI_STATE_KEY"

        // region TMP mock
        object TmpMock {
            val lastGameModelList = listOf(
                GameModel(name = "Starfinder"),
                GameModel(name = "Imperium 5"),
                GameModel(name = "Fallout"),
            )
            val lastSessionModelList = listOf(
                SessionModel(
                    title = "Camping à Castrovel",
                    status = SessionStatus.NOT_STARTED,
                    gameId = lastGameModelList[0].id,
                ),
                SessionModel(
                    title = "L'Atlantide, un monde oubliée",
                    status = SessionStatus.PENDING,
                    gameId = lastGameModelList[1].id,
                ),
                SessionModel(
                    title = "L'abris 118",
                    status = SessionStatus.FINISHED,
                    gameId = lastGameModelList[2].id,
                ),
            )

            val lastCharacterModelList = listOf(
                CharacterModel(
                    level = 1,
                    name = "David Jackson",
                    gameId = lastGameModelList[0].id,
                ),
                CharacterModel(
                    gameId = lastGameModelList[0].id,
                    level = 1,
                    name = "Poulpi 1.0",
                ),
                CharacterModel(
                    gameId = lastGameModelList[1].id,
                    level = 1,
                    name = "Alaxa",
                ),
            )

            val lastCharacterUIList = listOf(
                CharacterUI(
                    id = 0,
                    level = 11,
                    name = "David Jackson",
                    game = lastGameModelList[0],
                ),
                CharacterUI(
                    id = 1,
                    game = lastGameModelList[0],
                    level = 1,
                    name = "Poulpi 1.0",
                ),
                CharacterUI(
                    id = 2,
                    game = lastGameModelList[1],
                    level = 1,
                    name = "Alaxa",
                ),
            )
        }
        // endregion
    }
}
