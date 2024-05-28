package com.fvanaldewereld.rpgcompanion.ui.scenario.list.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ScenarioListModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ScenarioModel
import com.fvanaldewereld.rpgcompanion.common.ui.theme.RpgCompanionTheme
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory

@Composable
fun ScenarioListSuccess(
    scenarioListModel: ScenarioListModel,
    modifier: Modifier = Modifier,
    goToScenarioDetail: (scenarioId: Long) -> Unit = {},
    updateDeletingScenario: (scenarioModel: ScenarioModel) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier.padding(20.dp),
    ) {
        itemsIndexed(scenarioListModel.scenarioModels) { index, scenario ->
            ScenarioListItem(
                scenario = scenario,
                position = index,
                goToScenarioDetail = goToScenarioDetail,
                deleteScenario = { updateDeletingScenario(scenario) },
            )
            if (index < scenarioListModel.scenarioModels.lastIndex) HorizontalDivider()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScenarioDetailSuccessPreview() {
    RpgCompanionTheme {
        ScenarioListSuccess(
            scenarioListModel = ScenarioListModel(
                scenarioModels = listOf(ScenarioModelMockFactory.scenarioModelWithId),
            ),
        )
    }
}
