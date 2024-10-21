package com.fvanaldewereld.rpgcompanion.ui.home.viewModel.slice

import com.fvanaldewereld.rpgcompanion.ui.home.state.LastGameListState
import kotlinx.coroutines.flow.StateFlow

interface GetLastGameListSlice {
    var lastGameListStateFlow: StateFlow<LastGameListState?>

    suspend fun getLastGameList(number: Int)
}
