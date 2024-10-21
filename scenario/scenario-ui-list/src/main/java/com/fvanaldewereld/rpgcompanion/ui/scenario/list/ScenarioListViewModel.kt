package com.fvanaldewereld.rpgcompanion.ui.scenario.list

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ScenarioListModel
import com.fvanaldewereld.rpgcompanion.common.dispatchers.KDispatchers
import com.fvanaldewereld.rpgcompanion.lib.domain.scenario.useCase.AddScenarioUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.scenario.useCase.DeleteScenarioUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.scenario.useCase.GetScenarioByUrlUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.scenario.useCase.GetScenarioListUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class ScenarioListViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val addScenarioUseCase: AddScenarioUseCase,
    private val deleteScenarioUseCase: DeleteScenarioUseCase,
    private val getScenarioByUrlUseCase: GetScenarioByUrlUseCase,
    private val getScenarioListUseCase: GetScenarioListUseCase,
    private val dispatchers: KDispatchers,
) : ViewModel() {

    companion object {
        const val SCENARIO_LIST_UI_STATE_KEY = "SCENARIO_LIST_UI_STATE_KEY"
    }

    var uiStateFlow: StateFlow<ScenarioListUiState> =
        savedStateHandle.getStateFlow<ScenarioListUiState>(
            SCENARIO_LIST_UI_STATE_KEY,
            ScenarioListUiState.Loading,
        )

    init {
        if (uiStateFlow.value is ScenarioListUiState.Loading) {
            getScenarioList()
        }
    }

    @SuppressWarnings("kotlin:S1481")
    fun addScenario(scenarioUrl: String, onSuccess: (scenarioId: Long) -> Unit) {
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
                            onSuccess(scenarioId)
                        }
                    }.onFailure {
                        Log.e(
                            javaClass.simpleName,
                            it.localizedMessage ?: "An error has occured during addScenarioUseCase",
                        )
                    }
                }.onFailure {
                    Log.e(
                        javaClass.simpleName,
                        it.localizedMessage ?: "An error has occured during getScenarioByUrlUseCase",
                    )
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
                    .onFailure {
                        Log.e(
                            javaClass.simpleName,
                            it.localizedMessage ?: "An error has occured during getScenarioListUseCase",
                        )
                    }
            }
        }
    }
}
