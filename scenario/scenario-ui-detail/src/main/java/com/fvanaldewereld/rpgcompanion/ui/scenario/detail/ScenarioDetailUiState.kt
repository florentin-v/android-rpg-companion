package com.fvanaldewereld.rpgcompanion.ui.scenario.detail

import android.os.Parcelable
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ScenarioModel
import kotlinx.parcelize.Parcelize

sealed interface ScenarioDetailUiState : Parcelable {
    @Parcelize
    object Loading : ScenarioDetailUiState

    @Parcelize
    data class Success(val scenario: ScenarioModel) : ScenarioDetailUiState
}
