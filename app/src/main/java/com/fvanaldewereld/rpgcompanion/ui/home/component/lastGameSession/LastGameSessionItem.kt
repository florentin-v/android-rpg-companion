package com.fvanaldewereld.rpgcompanion.ui.home.component.lastGameSession

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
import com.fvanaldewereld.rpgcompanion.ui.home.viewModel.HomeViewModel.Companion.TmpMock
import com.fvanaldewereld.rpgcompanion.ui.home.component.GameSessionModel
import com.fvanaldewereld.rpgcompanion.ui.home.component.GameSessionStatus
import com.fvanaldewereld.rpgcompanion.ui.home.model.HomeScreenAction

@Composable
internal fun LastGameSessionItem(
    gameSessionModel: GameSessionModel,
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
                HomeScreenAction.LastGameSessionPressed(
                    gameSessionModel.id,
                ),
            )
        },
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text("- ${gameSessionModel.title}")
            Text("- ${gameSessionModel.game.name}")
            when (gameSessionModel.status) {
                GameSessionStatus.NOT_STARTED -> Text("- Start the game")
                GameSessionStatus.PENDING -> Text("- Resume the game")
                GameSessionStatus.FINISHED -> Text("- See the game report")
            }
            Text("- ...")
        }
    }
}

@Preview
@Composable
private fun LastGameSessionsItemPreview() {
    RpgCompanionTheme {
        LastGameSessionItem(
            gameSessionModel = TmpMock.lastGameSessionModels.first(),
            onHomeScreenAction = {},
        )
    }
}
