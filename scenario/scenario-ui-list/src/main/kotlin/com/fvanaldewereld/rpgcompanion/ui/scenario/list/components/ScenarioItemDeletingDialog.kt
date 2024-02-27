package com.fvanaldewereld.rpgcompanion.ui.scenario.list.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ScenarioModel
import com.fvanaldewereld.rpgcompanion.common.ui.theme.RpgCompanionTheme
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory
import com.fvanaldewereld.rpgcompanion.ui.scenario.list.R

@Composable
fun ScenarioItemDeletingDialog(
    scenario: ScenarioModel?,
    modifier: Modifier = Modifier,
    onConfirmation: (scenarioId: Long) -> Unit = {},
    onCancel: () -> Unit = {},
) {
    AlertDialog(
        modifier = modifier,
        icon = {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = stringResource(
                    id = R.string.scenarioList_success_dialog_warningIcon_contentDescription,
                ),
            )
        },
        title = {
            Text(text = stringResource(R.string.scenarioList_success_dialog_title))
        },
        text = {
            Text(
                text = stringResource(
                    R.string.scenarioList_success_dialog_description,
                    scenario?.title?.title.orEmpty(),
                ),
            )
        },
        onDismissRequest = {
            onCancel()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    scenario?.id?.let { onConfirmation(it) }
                },
            ) {
                Text(stringResource(R.string.scenarioList_success_dialog_confirm))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onCancel()
                },
            ) {
                Text(stringResource(R.string.scenarioList_success_dialog_cancel))
            }
        },
    )
}

@Preview
@Composable
private fun ScenarioItemDeletingDialogPreview() {
    RpgCompanionTheme {
        ScenarioItemDeletingDialog(
            scenario = ScenarioModelMockFactory.scenarioModelWithoutId,
        )
    }
}
