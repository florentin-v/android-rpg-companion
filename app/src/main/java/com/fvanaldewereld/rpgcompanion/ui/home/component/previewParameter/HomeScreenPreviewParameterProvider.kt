package com.fvanaldewereld.rpgcompanion.ui.home.component.previewParameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.fvanaldewereld.rpgcompanion.ui.home.model.LastScenarioUI
import com.fvanaldewereld.rpgcompanion.ui.home.state.HomeUIState
import com.fvanaldewereld.rpgcompanion.ui.home.viewModel.HomeViewModel

class HomeScreenPreviewParameterProvider : PreviewParameterProvider<HomeUIState> {
    override val values: Sequence<HomeUIState>
        get() = sequenceOf(
            HomeUIState.Loading,
            HomeUIState.Success(
                lastCharacterModels = HomeViewModel.Companion.TmpMock.lastCharacterModelList,
                lastGameModels = HomeViewModel.Companion.TmpMock.lastGameModelList,
                lastSessionModels = HomeViewModel.Companion.TmpMock.lastSessionModelList,
                lastScenarioUIs = listOf(
                    LastScenarioUI(
                        id = 1L,
                        title = "title",
                        author = "author",
                    ),
                ),
            ),
        )

}
