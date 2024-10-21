package com.fvanaldewereld.rpgcompanion.ui.home.component.lastScenario

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fvanaldewereld.rpgcompanion.common.ui.theme.RpgCompanionTheme
import com.fvanaldewereld.rpgcompanion.common.ui.theme.Typography
import com.fvanaldewereld.rpgcompanion.ui.home.model.HomeScreenAction
import com.fvanaldewereld.rpgcompanion.ui.home.model.LastScenarioUI
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun LastScenarioList(
    lastScenarioUIList: ImmutableList<LastScenarioUI>,
    onHomeScreenAction: (HomeScreenAction) -> Unit,
) {
    Column {
        Text("My last scenarios", style = Typography.titleLarge)
        lastScenarioUIList.takeIf { it.isNotEmpty() }?.let {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(vertical = 10.dp),
            ) {
                items(it) { lastScenarioUI ->
                    LastScenarioItem(
                        lastScenarioUI = lastScenarioUI,
                        onHomeScreenAction = onHomeScreenAction,
                    )
                }
            }
        } ?: Text("There is no scenario !")
    }
}

@Preview
@Composable
private fun LastScenarioListPreview() {
    RpgCompanionTheme {
        LastScenarioList(
            lastScenarioUIList = persistentListOf(
                LastScenarioUI(
                    title = "title",
                    author = "author",
                    id = 1L,
                ),
            ),
            onHomeScreenAction = {},
        )
    }
}
