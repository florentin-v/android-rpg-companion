package com.fvanaldewereld.rpgcompanion.ui.scenario.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fvanaldewereld.rpgcompanion.common.dispatchers.KDispatchers
import com.fvanaldewereld.rpgcompanion.common.navigation.SCENARIO_ID_KEY
import com.fvanaldewereld.rpgcompanion.lib.domain.scenario.usecases.GetScenarioByDocumentNameUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.context.GlobalContext

class ScenarioDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    companion object {
        const val SCENARIO_UI_STATE_KEY = "SCENARIO_UI_STATE_KEY"
    }

    private val getScenarioByDocumentNameUseCase: GetScenarioByDocumentNameUseCase by GlobalContext.get().inject()
    private val dispatchers: KDispatchers by GlobalContext.get().inject()

    private val scenarioId: String = checkNotNull(savedStateHandle[SCENARIO_ID_KEY])

    var scenarioDetailUiStateFlow: StateFlow<ScenarioDetailUiState> =
        savedStateHandle.getStateFlow<ScenarioDetailUiState>(
            SCENARIO_UI_STATE_KEY,
            ScenarioDetailUiState.Loading,
        )

    init {
        if (scenarioDetailUiStateFlow.value is ScenarioDetailUiState.Loading) {
            getScenarioDetail()
        }
    }

    private fun getScenarioDetail() {
        viewModelScope.launch {
            withContext(dispatchers.default()) {
                // TODO Catch SQLiteException
                kotlin.runCatching { getScenarioByDocumentNameUseCase(scenarioId.toLong()) }
                    .onSuccess { scenarioModel ->
                        savedStateHandle[SCENARIO_UI_STATE_KEY] =
                            ScenarioDetailUiState.Success(scenario = scenarioModel)
                    }
            }
        }
    }
}
