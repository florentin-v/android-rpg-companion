package com.fvanaldewereld.rpgcompanion.ui.home.viewModel.slice

import com.fvanaldewereld.rpgcompanion.lib.domain.session.useCase.AddSessionUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.session.useCase.GetLastSessionListUseCase
import com.fvanaldewereld.rpgcompanion.ui.home.state.LastSessionListState
import com.fvanaldewereld.rpgcompanion.ui.home.viewModel.HomeViewModel.Companion.TmpMock
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GetLastSessionListSliceImpl(
    private val addSessionUseCase: AddSessionUseCase, // TODO FVA remove after implementing the game creation flow
    private val getLastSessionListUseCase: GetLastSessionListUseCase,
) : GetLastSessionListSlice {
    private val _lastSessionListState = MutableStateFlow<LastSessionListState?>(null)
    override var lastSessionListStateFlow = _lastSessionListState.asStateFlow()

    override suspend fun getLastSessionList(number: Int) {
        _lastSessionListState.value = LastSessionListState.Loading

        // TODO FVA Remove this after implementing the session creation flow
        runCatching {
            addSessionUseCase(sessionModel = TmpMock.lastSessionModelList[0])
            addSessionUseCase(sessionModel = TmpMock.lastSessionModelList[1])
            addSessionUseCase(sessionModel = TmpMock.lastSessionModelList[2])
        }
        delay(2000)

        runCatching { getLastSessionListUseCase(number) }
            .onSuccess { sessionList ->
                _lastSessionListState.value = LastSessionListState.Success(
                    lastSessionList = sessionList,
                )
            }
            .onFailure { _lastSessionListState.value = LastSessionListState.Failure(it) }
    }
}
