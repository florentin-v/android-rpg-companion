package com.fvanaldewereld.rpgcompanion.ui.home.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ScenarioModel
import com.fvanaldewereld.rpgcompanion.common.dispatchers.KDispatchers
import com.fvanaldewereld.rpgcompanion.lib.domain.scenario.usecases.GetLastScenarioListUseCase
import com.fvanaldewereld.rpgcompanion.ui.home.component.CharacterModel
import com.fvanaldewereld.rpgcompanion.ui.home.component.GameModel
import com.fvanaldewereld.rpgcompanion.ui.home.component.GameSessionModel
import com.fvanaldewereld.rpgcompanion.ui.home.component.GameSessionStatus
import com.fvanaldewereld.rpgcompanion.ui.home.mapper.LastScenarioUIMapper
import com.fvanaldewereld.rpgcompanion.ui.home.state.HomeUIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.context.GlobalContext

class HomeViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    companion object {
        const val HOME_UI_STATE_KEY = "HOME_UI_STATE_KEY"


        // region TMP mock
        object TmpMock {
            val lastGameModels = listOf(GameModel(0, "Starfinder"), GameModel(1, "Imperium 5"))
            val lastGameSessionModels = listOf(
                GameSessionModel(
                    id = 0,
                    title = "Game session 0",
                    status = GameSessionStatus.NOT_STARTED,
                    game = lastGameModels[0],
                ),
                GameSessionModel(
                    id = 1,
                    title = "Game session 1",
                    status = GameSessionStatus.PENDING,
                    game = lastGameModels[0],
                ),
                GameSessionModel(
                    id = 2,
                    title = "Game session 2",
                    status = GameSessionStatus.FINISHED,
                    game = lastGameModels[1],
                ),
            )

            val lastCharacterModels = listOf(
                CharacterModel(
                    id = 0,
                    level = 1,
                    name = "David Jackson",
                    game = lastGameModels[0],
                ),
            )
        }
        // endregion
    }

    private val getLastScenarioListUseCase: GetLastScenarioListUseCase by GlobalContext.get().inject()
    private val dispatchers: KDispatchers by GlobalContext.get().inject()
    private val lastScenarioUIMapper: LastScenarioUIMapper by GlobalContext.get().inject()

    var homeUIStateFlow: StateFlow<HomeUIState> = savedStateHandle.getStateFlow<HomeUIState>(
        HOME_UI_STATE_KEY,
        HomeUIState.Loading,
    )

    private val lastCharacterModelsFlow = MutableStateFlow<List<CharacterModel>>(emptyList())
    private val lastGameModelsFlow = MutableStateFlow<List<GameModel>>(emptyList())
    private val lastGameSessionModelsFlow = MutableStateFlow<List<GameSessionModel>>(emptyList())
    private val lastScenarioModelsFlow = MutableStateFlow<List<ScenarioModel>>(emptyList())
    private val combinedFlow = combine(
        lastCharacterModelsFlow,
        lastGameModelsFlow,
        lastGameSessionModelsFlow,
        lastScenarioModelsFlow,
    ) { lastCharacterModels, lastGameModels, lastGameSessionModels, lastScenarioModels ->
        HomeUIState.Success(
            lastCharacterModels = lastCharacterModels,
            lastGameModels = lastGameModels,
            lastGameSessionModels = lastGameSessionModels,
            lastScenarioUIs = lastScenarioModels.mapNotNull(lastScenarioUIMapper::to),
        )
    }

    init {
        if (homeUIStateFlow.value is HomeUIState.Loading) {
            viewModelScope.launch {
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
                getLastCharacterModels()
                getLastGameModels()
                getLastGameSessionModels()
                getLastScenarioModels()
            }
        }
    }

    private suspend fun getLastScenarioModels() {
        // TODO Catch SQLiteException
        delay(2000)
        kotlin.runCatching { getLastScenarioListUseCase(number = 3) }
            .onSuccess { lastScenarioModels -> lastScenarioModelsFlow.value = lastScenarioModels }
    }

    private suspend fun getLastGameSessionModels() {
        delay(2000)
        lastGameSessionModelsFlow.value = TmpMock.lastGameSessionModels
    }

    private suspend fun getLastGameModels() {
        delay(2000)
        lastGameModelsFlow.value = TmpMock.lastGameModels
    }

    private suspend fun getLastCharacterModels() {
        delay(2000)
        lastCharacterModelsFlow.value = TmpMock.lastCharacterModels
    }
}
