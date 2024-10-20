package com.fvanaldewereld.rpgcompanion.ui.home.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fvanaldewereld.rpgcompanion.api.domain.character.model.CharacterModel
import com.fvanaldewereld.rpgcompanion.api.domain.game.model.GameModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ScenarioModel
import com.fvanaldewereld.rpgcompanion.api.domain.session.model.SessionModel
import com.fvanaldewereld.rpgcompanion.api.domain.session.model.SessionStatus
import com.fvanaldewereld.rpgcompanion.common.dispatchers.KDispatchers
import com.fvanaldewereld.rpgcompanion.lib.domain.character.useCase.AddCharacterUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.character.useCase.GetLastCharacterListUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.game.useCase.AddGameUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.game.useCase.GetGameByIdUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.game.useCase.GetLastGameListUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.scenario.useCase.GetLastScenarioListUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.session.useCase.AddSessionUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.session.useCase.GetLastSessionListUseCase
import com.fvanaldewereld.rpgcompanion.ui.home.mapper.LastCharacterUIMapper
import com.fvanaldewereld.rpgcompanion.ui.home.mapper.LastScenarioUIMapper
import com.fvanaldewereld.rpgcompanion.ui.home.model.CharacterUI
import com.fvanaldewereld.rpgcompanion.ui.home.state.HomeUIState
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val addCharacterUseCase: AddCharacterUseCase,
    private val addGameUseCase: AddGameUseCase,
    private val addSessionUseCase: AddSessionUseCase,
    private val getGameByIdUseCase: GetGameByIdUseCase,
    private val getLastCharacterListUseCase: GetLastCharacterListUseCase,
    private val getLastGameListUseCase: GetLastGameListUseCase,
    private val getLastScenarioListUseCase: GetLastScenarioListUseCase,
    private val getLastSessionListUseCase: GetLastSessionListUseCase,
    private val dispatchers: KDispatchers,
    private val lastScenarioUIMapper: LastScenarioUIMapper,
    private val lastCharacterUIMapper: LastCharacterUIMapper,
) : ViewModel() {

    companion object {
        const val HOME_UI_STATE_KEY = "HOME_UI_STATE_KEY"

        // region TMP mock
        object TmpMock {
            val lastGameModelList = persistentListOf(
                GameModel(name = "Starfinder"),
                GameModel(name = "Imperium 5"),
                GameModel(name = "Fallout"),
            )
            val lastSessionModelList = persistentListOf(
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

            val lastCharacterModelList = persistentListOf(
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

            val lastCharacterUIList = persistentListOf(
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

    var homeUIStateFlow: StateFlow<HomeUIState> = savedStateHandle.getStateFlow<HomeUIState>(
        HOME_UI_STATE_KEY,
        HomeUIState.Loading,
    )

    private val lastCharacterUIListFlow = MutableStateFlow<List<CharacterUI>>(emptyList())
    private val lastGameModelListFlow = MutableStateFlow<List<GameModel>>(emptyList())
    private val lastSessionModelListFlow = MutableStateFlow<List<SessionModel>>(emptyList())
    private val lastScenarioModelListFlow = MutableStateFlow<List<ScenarioModel>>(emptyList())

    private val combinedFlow = combine(
        lastCharacterUIListFlow,
        lastGameModelListFlow,
        lastSessionModelListFlow,
        lastScenarioModelListFlow,
    ) { lastCharacterUIList, lastGameModelList, lastSessionModelList, lastScenarioModelList ->
        HomeUIState.Success(
            lastCharacterUIList = lastCharacterUIList,
            lastGameModelList = lastGameModelList,
            lastSessionModelList = lastSessionModelList,
            lastScenarioUIList = lastScenarioModelList.mapNotNull(lastScenarioUIMapper::toUI),
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
                getLastCharacterUIList()
                getLastGameModelList()
                getLastSessionModelList()
                getLastScenarioModelList()
            }
        }
    }

    private suspend fun getLastScenarioModelList() {
        // TODO Catch SQLiteException
        delay(2000)
        kotlin.runCatching { getLastScenarioListUseCase(number = 3) }
            .onSuccess { lastScenarioModelList -> lastScenarioModelListFlow.value = lastScenarioModelList }
    }

    private suspend fun getLastSessionModelList() {
        // TODO FVA Remove this after implementing the session creation flow
        addSessionUseCase(sessionModel = TmpMock.lastSessionModelList[0])
        addSessionUseCase(sessionModel = TmpMock.lastSessionModelList[1])
        addSessionUseCase(sessionModel = TmpMock.lastSessionModelList[2])

        // TODO Catch SQLiteException
        delay(2000)
        kotlin.runCatching { getLastSessionListUseCase(number = 2) }
            .onSuccess { lastSessionModelList -> lastSessionModelListFlow.value = lastSessionModelList }
    }

    private suspend fun getLastGameModelList() {
        // TODO FVA Remove this after implementing the session creation flow
        addGameUseCase(gameModel = TmpMock.lastGameModelList[0])
        addGameUseCase(gameModel = TmpMock.lastGameModelList[1])
        addGameUseCase(gameModel = TmpMock.lastGameModelList[2])

        delay(2000)
        kotlin.runCatching { getLastGameListUseCase(number = 2) }
            .onSuccess { lastGameModelList -> lastGameModelListFlow.value = lastGameModelList }
    }

    private suspend fun getLastCharacterUIList() {
        // TODO FVA Remove this after implementing the character creation flow
        addCharacterUseCase(characterModel = TmpMock.lastCharacterModelList[0])
        addCharacterUseCase(characterModel = TmpMock.lastCharacterModelList[1])
        addCharacterUseCase(characterModel = TmpMock.lastCharacterModelList[2])

        // TODO Catch SQLiteException
        delay(2000)
        kotlin.runCatching { getLastCharacterListUseCase(number = 2) }
            .onSuccess { lastCharacterModelList ->
                val lastCharacterUIList = lastCharacterModelList.map { characterModel ->
                    val gameModel = characterModel.gameId?.let {
                        kotlin.runCatching { getGameByIdUseCase(it) }.getOrNull()
                    }
                    lastCharacterUIMapper.toUIWithGameModel(
                        from = characterModel,
                        gameModel = gameModel,
                    )
                }
                lastCharacterUIListFlow.value = lastCharacterUIList
            }
    }
}
