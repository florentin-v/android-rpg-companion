package com.fvanaldewereld.rpgcompanion.ui.home.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fvanaldewereld.rpgcompanion.api.domain.character.models.CharacterModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ScenarioModel
import com.fvanaldewereld.rpgcompanion.common.dispatchers.KDispatchers
import com.fvanaldewereld.rpgcompanion.lib.domain.character.usecases.AddCharacterUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.character.usecases.GetLastCharacterListUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.scenario.usecases.GetLastScenarioListUseCase
import com.fvanaldewereld.rpgcompanion.ui.home.component.GameModel
import com.fvanaldewereld.rpgcompanion.ui.home.component.SessionModel
import com.fvanaldewereld.rpgcompanion.ui.home.component.SessionStatus
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
            val lastSessionModels = listOf(
                SessionModel(
                    id = 0,
                    title = "Game session 0",
                    status = SessionStatus.NOT_STARTED,
                    game = lastGameModels[0],
                ),
                SessionModel(
                    id = 1,
                    title = "Game session 1",
                    status = SessionStatus.PENDING,
                    game = lastGameModels[0],
                ),
                SessionModel(
                    id = 2,
                    title = "Game session 2",
                    status = SessionStatus.FINISHED,
                    game = lastGameModels[1],
                ),
            )

            val lastCharacterModels = listOf(
                CharacterModel(
                    id = 0,
                    level = 1,
                    name = "David Jackson",
                    gameId = lastGameModels[0].id,
                ),
            )

        }
        // endregion
    }

    private val getLastScenarioListUseCase: GetLastScenarioListUseCase by GlobalContext.get().inject()
    private val getLastCharacterListUseCase: GetLastCharacterListUseCase by GlobalContext.get().inject()

    private val addCharacterUseCase: AddCharacterUseCase by GlobalContext.get().inject()

    private val dispatchers: KDispatchers by GlobalContext.get().inject()
    private val lastScenarioUIMapper: LastScenarioUIMapper by GlobalContext.get().inject()

    var homeUIStateFlow: StateFlow<HomeUIState> = savedStateHandle.getStateFlow<HomeUIState>(
        HOME_UI_STATE_KEY,
        HomeUIState.Loading,
    )

    private val lastCharacterModelsFlow = MutableStateFlow<List<CharacterModel>>(emptyList())
    private val lastGameModelsFlow = MutableStateFlow<List<GameModel>>(emptyList())
    private val lastSessionModelsFlow = MutableStateFlow<List<SessionModel>>(emptyList())
    private val lastScenarioModelsFlow = MutableStateFlow<List<ScenarioModel>>(emptyList())

    private val combinedFlow = combine(
        lastCharacterModelsFlow,
        lastGameModelsFlow,
        lastSessionModelsFlow,
        lastScenarioModelsFlow,
    ) { lastCharacterModels, lastGameModels, lastGameSessionModels, lastScenarioModels ->
        HomeUIState.Success(
            lastCharacterModels = lastCharacterModels,
            lastGameModels = lastGameModels,
            lastSessionModels = lastGameSessionModels,
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
            .onSuccess { lastScenarioModelList -> lastScenarioModelsFlow.value = lastScenarioModelList }
    }

    private suspend fun getLastGameSessionModels() {
        delay(2000)
        lastSessionModelsFlow.value = TmpMock.lastSessionModels
    }

    private suspend fun getLastGameModels() {
        delay(2000)
        lastGameModelsFlow.value = TmpMock.lastGameModels
    }

    private suspend fun getLastCharacterModels() {
        // TODO FVA Remove this after implementing the character creation flow
        addCharacterUseCase(
            characterModel = CharacterModel(
                gameId = TmpMock.lastGameModels[0].id,
                level = 1,
                name = "David Jackson",
            ),
        )
        addCharacterUseCase(
            characterModel = CharacterModel(
                gameId = TmpMock.lastGameModels[0].id,
                level = 1,
                name = "Poulpi 1.0",
            ),
        )
        addCharacterUseCase(
            characterModel = CharacterModel(
                gameId = TmpMock.lastGameModels[1].id,
                level = 1,
                name = "Alaxa",
            ),
        )

        // TODO Catch SQLiteException
        delay(2000)
        kotlin.runCatching { getLastCharacterListUseCase(number = 2) }
            .onSuccess { lastCharacterModelList -> lastCharacterModelsFlow.value = lastCharacterModelList }
    }
}
