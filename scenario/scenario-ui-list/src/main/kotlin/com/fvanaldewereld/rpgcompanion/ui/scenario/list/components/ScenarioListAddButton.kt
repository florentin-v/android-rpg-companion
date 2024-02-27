package com.fvanaldewereld.rpgcompanion.ui.scenario.list.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.fvanaldewereld.rpgcompanion.common.ui.theme.RpgCompanionTheme
import com.fvanaldewereld.rpgcompanion.ui.scenario.list.R

@Composable
internal fun ScenarioListAddButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier.clip(RoundedCornerShape(50)),
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(R.string.scenarioList_addButton_contentDescription),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ScenarioListAddButtonPreview() {
    RpgCompanionTheme {
        ScenarioListAddButton(onClick = {})
    }
}
