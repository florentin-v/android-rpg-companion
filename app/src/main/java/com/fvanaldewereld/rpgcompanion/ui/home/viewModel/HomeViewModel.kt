package com.fvanaldewereld.rpgcompanion.ui.home.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fvanaldewereld.rpgcompanion.api.domain.character.model.CharacterModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ScenarioModel
import com.fvanaldewereld.rpgcompanion.api.domain.session.model.SessionModel
import com.fvanaldewereld.rpgcompanion.api.domain.session.model.SessionStatus
import com.fvanaldewereld.rpgcompanion.common.dispatchers.KDispatchers
import com.fvanaldewereld.rpgcompanion.lib.domain.character.useCase.AddCharacterUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.character.useCase.GetLastCharacterListUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.scenario.useCase.GetLastScenarioListUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.session.useCase.AddSessionUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.session.useCase.GetLastSessionListUseCase
import com.fvanaldewereld.rpgcompanion.ui.home.component.GameModel
import com.fvanaldewereld.rpgcompanion.ui.home.mapper.LastScenarioUIMapper
import com.fvanaldewereld.rpgcompanion.ui.home.state.HomeUIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val addCharacterUseCase: AddCharacterUseCase,
    private val addSessionUseCase: AddSessionUseCase,
    private val getLastCharacterListUseCase: GetLastCharacterListUseCase,
    private val getLastScenarioListUseCase: GetLastScenarioListUseCase,
    private val getLastSessionListUseCase: GetLastSessionListUseCase,
    private val dispatchers: KDispatchers,
    private val lastScenarioUIMapper: LastScenarioUIMapper,
) : ViewModel() {

    companion object {
        const val HOME_UI_STATE_KEY = "HOME_UI_STATE_KEY"

        // region TMP mock
        object TmpMock {
            val lastGameModelList = listOf(
                GameModel(0, "Starfinder"),
                GameModel(1, "Imperium 5"),
            )
            val lastSessionModelList = listOf(
                SessionModel(
                    title = "Game session 0",
                    status = SessionStatus.NOT_STARTED,
                    gameId = lastGameModelList[0].id,
                ),
                SessionModel(
                    title = "Game session 1",
                    status = SessionStatus.PENDING,
                    gameId = lastGameModelList[0].id,
                ),
                SessionModel(
                    title = "Game session 2",
                    status = SessionStatus.FINISHED,
                    gameId = lastGameModelList[1].id,
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
        }
        // endregion
    }

    var homeUIStateFlow: StateFlow<HomeUIState> = savedStateHandle.getStateFlow<HomeUIState>(
        HOME_UI_STATE_KEY,
        HomeUIState.Loading,
    )

    private val lastCharacterModelListFlow = MutableStateFlow<List<CharacterModel>>(emptyList())
    private val lastGameModelListFlow = MutableStateFlow<List<GameModel>>(emptyList())
    private val lastSessionModelListFlow = MutableStateFlow<List<SessionModel>>(emptyList())
    private val lastScenarioModelListFlow = MutableStateFlow<List<ScenarioModel>>(emptyList())

    private val combinedFlow = combine(
        lastCharacterModelListFlow,
        lastGameModelListFlow,
        lastSessionModelListFlow,
        lastScenarioModelListFlow,
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
                getLastSessionModels()
                getLastScenarioModels()
            }
        }
    }

    private suspend fun getLastScenarioModels() {
        // TODO Catch SQLiteException
        delay(2000)
        kotlin.runCatching { getLastScenarioListUseCase(number = 3) }
            .onSuccess { lastScenarioModelList -> lastScenarioModelListFlow.value = lastScenarioModelList }
    }

    private suspend fun getLastSessionModels() {
        // TODO FVA Remove this after implementing the character creation flow
        addSessionUseCase(sessionModel = TmpMock.lastSessionModelList[0])
        addSessionUseCase(sessionModel = TmpMock.lastSessionModelList[1])
        addSessionUseCase(sessionModel = TmpMock.lastSessionModelList[2])

        // TODO Catch SQLiteException
        delay(2000)
        kotlin.runCatching { getLastSessionListUseCase(number = 2) }
            .onSuccess { lastSessionModelList -> lastSessionModelListFlow.value = lastSessionModelList }
    }

    private suspend fun getLastGameModels() {
        delay(2000)
        lastGameModelListFlow.value = TmpMock.lastGameModelList
    }

    private suspend fun getLastCharacterModels() {
        // TODO FVA Remove this after implementing the character creation flow
        addCharacterUseCase(characterModel = TmpMock.lastCharacterModelList[0])
        addCharacterUseCase(characterModel = TmpMock.lastCharacterModelList[1])
        addCharacterUseCase(characterModel = TmpMock.lastCharacterModelList[2])

        // TODO Catch SQLiteException
        delay(2000)
        kotlin.runCatching { getLastCharacterListUseCase(number = 2) }
            .onSuccess { lastCharacterModelList -> lastCharacterModelListFlow.value = lastCharacterModelList }
    }
}
