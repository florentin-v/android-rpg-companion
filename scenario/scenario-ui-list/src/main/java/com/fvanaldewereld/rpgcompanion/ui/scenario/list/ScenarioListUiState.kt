package com.fvanaldewereld.rpgcompanion.ui.scenario.list

import android.os.Parcelable
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ScenarioListModel
import kotlinx.parcelize.Parcelize

sealed interface ScenarioListUiState : Parcelable {

    @Parcelize
    data class Error(val errorMessage: String) : ScenarioListUiState

    @Parcelize
    data object Loading : ScenarioListUiState

    @Parcelize
    data object NoResult : ScenarioListUiState

    @Parcelize
    data class Success(val scenarioListModel: ScenarioListModel) : ScenarioListUiState
}
