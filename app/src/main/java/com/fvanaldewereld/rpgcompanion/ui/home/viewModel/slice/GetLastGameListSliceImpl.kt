package com.fvanaldewereld.rpgcompanion.ui.home.viewModel.slice

import com.fvanaldewereld.rpgcompanion.lib.domain.game.useCase.AddGameUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.game.useCase.GetLastGameListUseCase
import com.fvanaldewereld.rpgcompanion.ui.home.state.LastGameListState
import com.fvanaldewereld.rpgcompanion.ui.home.viewModel.HomeViewModel.Companion.TmpMock
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GetLastGameListSliceImpl(
    private val addGameUseCase: AddGameUseCase, // TODO FVA remove after implementing the game creation flow
    private val getLastGameListUseCase: GetLastGameListUseCase,
) : GetLastGameListSlice {
    private val _lastGameListState = MutableStateFlow<LastGameListState?>(null)
    override var lastGameListStateFlow = _lastGameListState.asStateFlow()

    override suspend fun getLastGameList(number: Int) {
        _lastGameListState.value = LastGameListState.Loading

        // TODO FVA Remove this after implementing the session creation flow
        runCatching {
            addGameUseCase(gameModel = TmpMock.lastGameModelList[0])
            addGameUseCase(gameModel = TmpMock.lastGameModelList[1])
            addGameUseCase(gameModel = TmpMock.lastGameModelList[2])
        }
        delay(2_000L)

        runCatching { getLastGameListUseCase(number) }
            .onSuccess { gameList ->
                _lastGameListState.value = LastGameListState.Success(
                    lastGameList = gameList,
                )
            }
            .onFailure { _lastGameListState.value = LastGameListState.Failure(it) }
    }
}
