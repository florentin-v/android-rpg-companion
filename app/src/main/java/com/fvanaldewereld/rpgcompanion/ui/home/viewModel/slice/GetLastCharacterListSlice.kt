package com.fvanaldewereld.rpgcompanion.ui.home.viewModel.slice

import com.fvanaldewereld.rpgcompanion.ui.home.state.LastCharacterListState
import kotlinx.coroutines.flow.StateFlow

interface GetLastCharacterListSlice {
    var lastCharacterListStateFlow: StateFlow<LastCharacterListState?>

    suspend fun getLastCharacterList(number: Int)
}
