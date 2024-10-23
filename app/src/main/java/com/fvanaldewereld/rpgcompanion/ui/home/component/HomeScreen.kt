package com.fvanaldewereld.rpgcompanion.ui.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.fvanaldewereld.rpgcompanion.common.ui.theme.RpgCompanionTheme
import com.fvanaldewereld.rpgcompanion.ui.home.component.previewParameter.HomeScreenPreviewParameterProvider
import com.fvanaldewereld.rpgcompanion.ui.home.model.HomeScreenAction
import com.fvanaldewereld.rpgcompanion.ui.home.state.HomeUIState

@Composable
internal fun HomeScreen(
    uiState: HomeUIState,
    onHomeScreenAction: (HomeScreenAction) -> Unit,
) {
    Scaffold(
        topBar = { HomeScreenTopAppBar(onHomeScreenAction) },
        bottomBar = { HomeScreenBottomNavBar(onHomeScreenAction) },
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues),
        ) {
            when (uiState) {
                HomeUIState.Loading -> HomeScreenLoading()
                is HomeUIState.Success -> HomeScreenSuccess(
                    state = uiState,
                    onHomeScreenAction = onHomeScreenAction,
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview(
    @PreviewParameter(HomeScreenPreviewParameterProvider::class) homeUIState: HomeUIState,
) {
    RpgCompanionTheme {
        HomeScreen(
            uiState = homeUIState,
            onHomeScreenAction = {},
        )
    }
}
