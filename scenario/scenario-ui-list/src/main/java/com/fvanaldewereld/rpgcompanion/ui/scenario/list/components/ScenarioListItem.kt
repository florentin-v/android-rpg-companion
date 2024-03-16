package com.fvanaldewereld.rpgcompanion.ui.scenario.list.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ScenarioModel
import com.fvanaldewereld.rpgcompanion.common.ui.theme.RpgCompanionTheme
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory
import com.fvanaldewereld.rpgcompanion.ui.scenario.list.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun ScenarioListItem(
    scenario: ScenarioModel,
    modifier: Modifier = Modifier,
    goToScenarioDetail: (scenarioId: Long) -> Unit = {},
    deleteScenario: (scenarioId: Long) -> Unit = {},
) {
    val bubbleColor = MaterialTheme.colorScheme.primaryContainer
    val bubbleLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
    val deleteButtonColor = MaterialTheme.colorScheme.secondary

    scenario.id?.let { scenarioId ->
        ListItem(
            leadingContent = {
                Box(modifier = Modifier.size(40.dp), contentAlignment = Alignment.Center) {
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        drawCircle(bubbleColor)
                    }
                    Text("$scenarioId", style = TextStyle(color = bubbleLabelColor))
                }
            },
            headlineContent = {
                Text(
                    scenario.mainInfo.title.value ?: stringResource(id = R.string.scenarioList_page_scenario_noTitle),
                )
            },
            trailingContent = {
                IconButton(onClick = { deleteScenario(scenarioId) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(
                            R.string.scenarioList_success_deleteButton_contentDescription,
                            scenarioId,
                        ),
                        tint = deleteButtonColor,
                    )
                }
            },
            modifier = modifier.clickable { goToScenarioDetail(scenarioId) },
            supportingContent = {
                FlowRow(
                    verticalArrangement = Arrangement.Center,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxWidth(),
                    maxItemsInEachRow = 3,
                ) {
                    scenario.mainInfo.information.genres.values.forEach { genre ->
                        ScenarioListItemChips(genre)
                    }
                }
            },
        )
    }
}

@Preview
@Composable
private fun ScenarioListItemPreview() {
    RpgCompanionTheme {
        ScenarioListItem(scenario = ScenarioModelMockFactory.scenarioModelWithId)
    }
}
