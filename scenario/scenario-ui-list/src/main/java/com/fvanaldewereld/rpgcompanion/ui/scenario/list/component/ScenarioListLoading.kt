package com.fvanaldewereld.rpgcompanion.ui.scenario.list.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fvanaldewereld.rpgcompanion.common.ui.theme.RpgCompanionTheme

@Composable
internal fun ScenarioListLoading(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(size = 64.dp),
            strokeWidth = 6.dp,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ScenarioDetailLoadingPreview() {
    RpgCompanionTheme {
        ScenarioListLoading()
    }
}
