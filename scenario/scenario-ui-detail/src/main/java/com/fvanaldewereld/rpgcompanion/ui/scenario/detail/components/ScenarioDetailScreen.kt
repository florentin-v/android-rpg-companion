package com.fvanaldewereld.rpgcompanion.ui.scenario.detail.components

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fvanaldewereld.rpgcompanion.ui.scenario.detail.ScenarioDetailUiState
import com.fvanaldewereld.rpgcompanion.ui.scenario.detail.ScenarioDetailViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ScenarioDetailScreen(
    viewModel: ScenarioDetailViewModel = koinViewModel(),
    onBackButtonPressed: () -> Unit = {},
) {
    when (val state = viewModel.scenarioDetailUiStateFlow.collectAsStateWithLifecycle().value) {
        is ScenarioDetailUiState.Loading ->
            ScenarioDetailLoading()

        is ScenarioDetailUiState.Success ->
            ScenarioDetailSuccess(
                scenario = state.scenario,
                onBackButtonPressed = onBackButtonPressed,
            )
    }
}
