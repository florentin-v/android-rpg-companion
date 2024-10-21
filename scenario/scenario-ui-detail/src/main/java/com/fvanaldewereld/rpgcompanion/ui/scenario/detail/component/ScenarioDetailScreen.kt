package com.fvanaldewereld.rpgcompanion.ui.scenario.detail.component

import androidx.compose.runtime.Composable
import com.fvanaldewereld.rpgcompanion.ui.scenario.detail.ScenarioDetailUiState

@Composable
fun ScenarioDetailScreen(
    uiState: ScenarioDetailUiState,
    onBackButtonPressed: () -> Unit = {},
) {
    when (uiState) {
        is ScenarioDetailUiState.Loading ->
            ScenarioDetailLoading()

        is ScenarioDetailUiState.Success ->
            ScenarioDetailSuccess(
                scenario = uiState.scenario,
                onBackButtonPressed = onBackButtonPressed,
            )
    }
}
