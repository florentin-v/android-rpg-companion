package com.fvanaldewereld.rpgcompanion.ui.home.viewModel.slice

import com.fvanaldewereld.rpgcompanion.lib.domain.scenario.useCase.GetLastScenarioListUseCase
import com.fvanaldewereld.rpgcompanion.ui.home.mapper.LastScenarioUIMapper
import com.fvanaldewereld.rpgcompanion.ui.home.state.LastScenarioListState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GetLastScenarioListSliceImpl(
    private val getLastScenarioListUseCase: GetLastScenarioListUseCase,
    private val lastScenarioUIMapper: LastScenarioUIMapper,
) : GetLastScenarioListSlice {
    private val _lastScenarioListState = MutableStateFlow<LastScenarioListState?>(null)
    override var lastScenarioListStateFlow = _lastScenarioListState.asStateFlow()

    override suspend fun getLastScenarioList(number: Int) {
        _lastScenarioListState.value = LastScenarioListState.Loading
        delay(2_000L)
        kotlin.runCatching { getLastScenarioListUseCase(number) }
            .onSuccess { scenarioList ->
                _lastScenarioListState.value = LastScenarioListState.Success(
                    lastScenarioList = scenarioList.mapNotNull(lastScenarioUIMapper::toUI),
                )
            }
            .onFailure { _lastScenarioListState.value = LastScenarioListState.Failure(it) }
    }
}
