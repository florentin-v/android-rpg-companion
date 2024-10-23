package com.fvanaldewereld.rpgcompanion.ui.home.viewModel.slice

import com.fvanaldewereld.rpgcompanion.ui.home.state.LastScenarioListState
import kotlinx.coroutines.flow.StateFlow

interface GetLastScenarioListSlice {
    var lastScenarioListStateFlow: StateFlow<LastScenarioListState?>

    suspend fun getLastScenarioList(number: Int)
}
