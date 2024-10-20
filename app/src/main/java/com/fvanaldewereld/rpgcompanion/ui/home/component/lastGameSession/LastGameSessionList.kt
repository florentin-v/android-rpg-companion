package com.fvanaldewereld.rpgcompanion.ui.home.component.lastGameSession

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fvanaldewereld.rpgcompanion.api.domain.session.model.SessionModel
import com.fvanaldewereld.rpgcompanion.common.ui.theme.RpgCompanionTheme
import com.fvanaldewereld.rpgcompanion.common.ui.theme.Typography
import com.fvanaldewereld.rpgcompanion.ui.home.model.HomeScreenAction
import com.fvanaldewereld.rpgcompanion.ui.home.viewModel.HomeViewModel.Companion.TmpMock
import kotlinx.collections.immutable.ImmutableList

@Composable
internal fun LastGameSessionList(
    lastSessionModels: ImmutableList<SessionModel>,
    onHomeScreenAction: (HomeScreenAction) -> Unit,
) {
    Column {
        Text("My last game sessions", style = Typography.titleLarge)
        lastSessionModels.ifEmpty { null }?.let {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(vertical = 10.dp),
            ) {
                items(it) { gameModel ->
                    LastGameSessionItem(gameModel, onHomeScreenAction)
                }
            }
        } ?: Text("There is no game session !")
    }
}

@Preview
@Composable
private fun LastGameSessionListPreview() {
    RpgCompanionTheme {
        LastGameSessionList(
            lastSessionModels = TmpMock.lastSessionModelList,
            onHomeScreenAction = {},
        )
    }
}
