package com.fvanaldewereld.rpgcompanion.ui.scenario.list.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.fvanaldewereld.rpgcompanion.common.ui.theme.RpgCompanionTheme
import com.fvanaldewereld.rpgcompanion.ui.scenario.list.R

@Composable
fun ScenarioListError(
    errorMessage: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = stringResource(R.string.scenarioList_error_text, errorMessage))
    }
}

@Preview(showBackground = true)
@Composable
private fun ScenarioListErrorPreview() {
    RpgCompanionTheme {
        ScenarioListError(errorMessage = "An error has occured !")
    }
}
