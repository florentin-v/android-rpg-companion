package com.fvanaldewereld.rpgcompanion.ui.home.component.lastScenario

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fvanaldewereld.rpgcompanion.common.ui.theme.RpgCompanionTheme
import com.fvanaldewereld.rpgcompanion.ui.home.model.HomeScreenAction
import com.fvanaldewereld.rpgcompanion.ui.home.model.LastScenarioUI

@Composable
internal fun LastScenarioItem(
    lastScenarioUI: LastScenarioUI,
    onHomeScreenAction: (HomeScreenAction) -> Unit,
) {
    ElevatedCard(
        modifier = Modifier
            .width(150.dp)
            .fillMaxHeight()
            .aspectRatio(1F),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
        ),
        onClick = {
            onHomeScreenAction(
                HomeScreenAction.LastScenarioPressed(
                    lastScenarioUI.id,
                ),
            )
        },
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text("- ${lastScenarioUI.title}")
            Text("- ${lastScenarioUI.author}")
            Text("- ...")
        }
    }
}

@Preview
@Composable
private fun LastScenarioItemPreview() {
    RpgCompanionTheme {
        LastScenarioItem(
            lastScenarioUI = LastScenarioUI(
                title = "title",
                author = "author",
                id = 1L,
            ),
            onHomeScreenAction = {},
        )
    }
}
