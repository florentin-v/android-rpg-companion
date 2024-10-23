package com.fvanaldewereld.rpgcompanion.ui.home.viewModel.slice

import com.fvanaldewereld.rpgcompanion.ui.home.state.LastSessionListState
import kotlinx.coroutines.flow.StateFlow

interface GetLastSessionListSlice {
    var lastSessionListStateFlow: StateFlow<LastSessionListState?>

    suspend fun getLastSessionList(number: Int)
}
