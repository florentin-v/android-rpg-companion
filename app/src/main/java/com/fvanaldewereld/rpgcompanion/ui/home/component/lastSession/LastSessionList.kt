package com.fvanaldewereld.rpgcompanion.ui.home.component.lastSession

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
import kotlinx.collections.immutable.toPersistentList

@Composable
internal fun LastSessionList(
    lastSessionModelList: ImmutableList<SessionModel>,
    onHomeScreenAction: (HomeScreenAction) -> Unit,
) {
    Column {
        Text("My last sessions", style = Typography.titleLarge)
        lastSessionModelList.takeIf { it.isNotEmpty() }?.let {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(vertical = 10.dp),
            ) {
                items(it) { gameModel ->
                    LastSessionItem(gameModel, onHomeScreenAction)
                }
            }
        } ?: Text("There is no session !")
    }
}

@Preview
@Composable
private fun LastSessionListPreview() {
    RpgCompanionTheme {
        LastSessionList(
            lastSessionModelList = TmpMock.lastSessionModelList.toPersistentList(),
            onHomeScreenAction = {},
        )
    }
}
