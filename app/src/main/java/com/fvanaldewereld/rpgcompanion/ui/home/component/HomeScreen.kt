package com.fvanaldewereld.rpgcompanion.ui.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fvanaldewereld.rpgcompanion.ui.home.model.HomeScreenAction
import com.fvanaldewereld.rpgcompanion.ui.home.state.HomeUIState
import com.fvanaldewereld.rpgcompanion.ui.home.viewModel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    onHomeScreenAction: (HomeScreenAction) -> Unit,
) {
    val uiState = viewModel.homeUIStateFlow.collectAsStateWithLifecycle().value
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
