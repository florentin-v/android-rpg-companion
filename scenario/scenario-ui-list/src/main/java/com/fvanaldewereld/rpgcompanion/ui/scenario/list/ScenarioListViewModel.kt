package com.fvanaldewereld.rpgcompanion.ui.scenario.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ScenarioListModel
import com.fvanaldewereld.rpgcompanion.common.dispatchers.KDispatchers
import com.fvanaldewereld.rpgcompanion.lib.domain.scenario.usecases.AddScenarioUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.scenario.usecases.DeleteScenarioUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.scenario.usecases.GetScenarioByUrlUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.scenario.usecases.GetScenarioListUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.context.GlobalContext
import java.net.URL

class ScenarioListViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    companion object {
        const val SCENARIO_LIST_UI_STATE_KEY = "SCENARIO_LIST_UI_STATE_KEY"
    }

    private val addScenarioUseCase: AddScenarioUseCase by GlobalContext.get().inject()
    private val deleteScenarioUseCase: DeleteScenarioUseCase by GlobalContext.get().inject()
    private val getScenarioListUseCase: GetScenarioListUseCase by GlobalContext.get().inject()
    private val dispatchers: KDispatchers by GlobalContext.get().inject()

    var scenarioListUiStateFlow: StateFlow<ScenarioListUiState> =
        savedStateHandle.getStateFlow<ScenarioListUiState>(
            SCENARIO_LIST_UI_STATE_KEY,
            ScenarioListUiState.Loading,
        )

    init {
        if (scenarioListUiStateFlow.value is ScenarioListUiState.Loading) {
            getScenarioList()
        }
    }

    @SuppressWarnings("kotlin:S1481")
    fun addScenario(scenarioUrl: String, goToScenarioDetail: (scenarioId: Long) -> Unit) {
        val getScenarioByUrlUseCase by GlobalContext.get().inject<GetScenarioByUrlUseCase>()
        viewModelScope.launch {
            savedStateHandle[SCENARIO_LIST_UI_STATE_KEY] = ScenarioListUiState.Loading
            withContext(dispatchers.default()) {
                kotlin.runCatching {
                    getScenarioByUrlUseCase(documentUrl = URL(scenarioUrl))
                }.onSuccess { scenarioModel ->
                    kotlin.runCatching {
                        addScenarioUseCase(scenarioModel)
                    }.onSuccess { scenarioId ->
                        getScenarioList()
                        withContext(dispatchers.main()) {
                            goToScenarioDetail(scenarioId)
                        }
                    }
                }
            }
        }
    }

    fun deleteScenario(scenarioId: Long) {
        viewModelScope.launch {
            savedStateHandle[SCENARIO_LIST_UI_STATE_KEY] = ScenarioListUiState.Loading
            withContext(dispatchers.default()) {
                kotlin.runCatching {
                    deleteScenarioUseCase(scenarioId = scenarioId)
                }.onSuccess { getScenarioList() }
            }
        }
    }

    private fun getScenarioList() {
        viewModelScope.launch {
            withContext(dispatchers.default()) {
                // TODO Catch SQLiteException
                kotlin.runCatching { getScenarioListUseCase() }
                    .onSuccess { scenarios ->
                        savedStateHandle[SCENARIO_LIST_UI_STATE_KEY] = if (scenarios.isNotEmpty()) {
                            ScenarioListUiState.Success(
                                scenarioListModel = ScenarioListModel(
                                    scenarioModels = scenarios,
                                ),
                            )
                        } else {
                            ScenarioListUiState.NoResult
                        }
                    }
            }
        }
    }
}
