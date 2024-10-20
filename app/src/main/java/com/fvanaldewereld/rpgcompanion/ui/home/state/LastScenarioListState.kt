package com.fvanaldewereld.rpgcompanion.ui.home.state

import android.os.Parcelable
import com.fvanaldewereld.rpgcompanion.ui.home.model.LastScenarioUI
import kotlinx.parcelize.Parcelize

sealed class LastScenarioListState : Parcelable {
    @Parcelize
    data object Loading : LastScenarioListState()

    @Parcelize
    data class Success(val lastScenarioList: List<LastScenarioUI>) : LastScenarioListState()

    @Parcelize
    data class Failure(val exception: Throwable) : LastScenarioListState()
}
