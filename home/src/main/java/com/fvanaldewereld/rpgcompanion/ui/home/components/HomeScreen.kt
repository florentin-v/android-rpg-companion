package com.fvanaldewereld.rpgcompanion.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fvanaldewereld.rpgcompanion.common.ui.components.RpgCompanionTopAppBar
import com.fvanaldewereld.rpgcompanion.ui.home.R

@Composable
fun HomeScreen(
    onGoToScenarioListButtonPressed: () -> Unit,
) {
    Scaffold(
        topBar = {
            RpgCompanionTopAppBar(
                title = "Home",
                modifier = Modifier.testTag(HomeTestTag.TopAppBar.value),
            )
        },
        modifier = Modifier.testTag(HomeTestTag.Screen.value),
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            Text(stringResource(R.string.home_page_welcome_text), modifier = Modifier.padding(vertical = 20.dp))
            Button(onClick = onGoToScenarioListButtonPressed, modifier = Modifier.testTag(HomeTestTag.Button.value)) {
                Text(stringResource(R.string.home_page_go_to_scenario_list_button_label))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun DefaultPreview() {
    HomeScreen(
        onGoToScenarioListButtonPressed = {},
    )
}
