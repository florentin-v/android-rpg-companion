package com.fvanaldewereld.rpgcompanion.ui.scenario.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.fvanaldewereld.rpgcompanion.common.dispatchers.KDispatchers
import com.fvanaldewereld.rpgcompanion.common.navigation.NavigationRoute
import com.fvanaldewereld.rpgcompanion.lib.domain.scenario.useCase.GetScenarioByDocumentNameUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScenarioDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getScenarioByDocumentNameUseCase: GetScenarioByDocumentNameUseCase,
    private val dispatchers: KDispatchers,
) : ViewModel() {

    companion object {
        const val SCENARIO_UI_STATE_KEY = "SCENARIO_UI_STATE_KEY"
    }

    private val scenarioId: Long
        get() = savedStateHandle.toRoute<NavigationRoute.ScenarioDetail>().scenarioId

    var uiStateFlow: StateFlow<ScenarioDetailUiState> =
        savedStateHandle.getStateFlow<ScenarioDetailUiState>(
            SCENARIO_UI_STATE_KEY,
            ScenarioDetailUiState.Loading,
        )

    init {
        if (uiStateFlow.value is ScenarioDetailUiState.Loading) {
            getScenarioDetail()
        }
    }

    private fun getScenarioDetail() {
        viewModelScope.launch {
            withContext(dispatchers.default()) {
                // TODO Catch SQLiteException
                kotlin.runCatching { getScenarioByDocumentNameUseCase(scenarioId) }
                    .onSuccess { scenarioModel ->
                        savedStateHandle[SCENARIO_UI_STATE_KEY] =
                            ScenarioDetailUiState.Success(scenario = scenarioModel)
                    }
            }
        }
    }
}
