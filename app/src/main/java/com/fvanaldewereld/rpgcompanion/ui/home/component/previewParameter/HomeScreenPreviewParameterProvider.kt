package com.fvanaldewereld.rpgcompanion.ui.home.component.previewParameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.fvanaldewereld.rpgcompanion.ui.home.model.LastScenarioUI
import com.fvanaldewereld.rpgcompanion.ui.home.state.HomeUIState
import com.fvanaldewereld.rpgcompanion.ui.home.viewModel.HomeViewModel
import kotlinx.collections.immutable.persistentListOf

class HomeScreenPreviewParameterProvider : PreviewParameterProvider<HomeUIState> {
    override val values: Sequence<HomeUIState>
        get() = sequenceOf(
            HomeUIState.Loading,
            HomeUIState.Success(
                lastCharacterUIList = HomeViewModel.Companion.TmpMock.lastCharacterUIList,
                lastGameModelList = HomeViewModel.Companion.TmpMock.lastGameModelList,
                lastSessionModelList = HomeViewModel.Companion.TmpMock.lastSessionModelList,
                lastScenarioUIList = persistentListOf(
                    LastScenarioUI(
                        id = 1L,
                        title = "title",
                        author = "author",
                    ),
                ),
            ),
        )
}
