package com.fvanaldewereld.rpgcompanion.ui.home.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.fvanaldewereld.rpgcompanion.common.ui.theme.RpgCompanionTheme
import com.fvanaldewereld.rpgcompanion.ui.home.model.HomeScreenAction

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun HomeScreenTopAppBar(onTopAppBarAction: (HomeScreenAction.TopAppBarAction) -> Unit) {
    val topAppBarActions = listOf(
        HomeScreenTopAppBarAction(
            imageVector = Icons.Default.Search,
            onClick = { onTopAppBarAction(HomeScreenAction.TopAppBarAction.SearchPressed) },
        ),
        HomeScreenTopAppBarAction(
            imageVector = Icons.Default.Person,
            onClick = { onTopAppBarAction(HomeScreenAction.TopAppBarAction.ProfilePressed) },
        ),
    )
    TopAppBar(
        title = { Text("RPG Companion") },
        actions = {
            topAppBarActions.map {
                IconButton(onClick = it.onClick) { Icon(it.imageVector, "") }
            }
        },
    )
}

@Preview
@Composable
private fun HomeScreenTopAppBarPreview() {
    RpgCompanionTheme {
        HomeScreenTopAppBar(
            onTopAppBarAction = {},
        )
    }
}
