package com.fvanaldewereld.rpgcompanion.ui.home.component.lastSession

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
import com.fvanaldewereld.rpgcompanion.api.domain.session.model.SessionModel
import com.fvanaldewereld.rpgcompanion.api.domain.session.model.SessionStatus
import com.fvanaldewereld.rpgcompanion.common.ui.theme.RpgCompanionTheme
import com.fvanaldewereld.rpgcompanion.ui.home.model.HomeScreenAction
import com.fvanaldewereld.rpgcompanion.ui.home.viewModel.HomeViewModel.Companion.TmpMock

@Composable
internal fun LastSessionItem(
    sessionModel: SessionModel,
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
                    sessionModel.id,
                ),
            )
        },
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text("- ${sessionModel.title}")
            Text("- ${sessionModel.gameId}")
            when (sessionModel.status) {
                SessionStatus.NOT_STARTED -> Text("- Start the game")
                SessionStatus.PENDING -> Text("- Resume the game")
                SessionStatus.FINISHED -> Text("- See the game report")
            }
            Text("- ...")
        }
    }
}

@Preview
@Composable
private fun LastSessionsItemPreview() {
    RpgCompanionTheme {
        LastSessionItem(
            sessionModel = TmpMock.lastSessionModelList.first(),
            onHomeScreenAction = {},
        )
    }
}
