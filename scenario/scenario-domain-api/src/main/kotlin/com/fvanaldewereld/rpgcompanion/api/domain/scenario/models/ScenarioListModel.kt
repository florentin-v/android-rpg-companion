package com.fvanaldewereld.rpgcompanion.api.domain.scenario.models

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class ScenarioListModel(
    val scenarioModels: List<ScenarioModel>,
) : Parcelable, Model
