package com.fvanaldewereld.rpgcompanion.ui.home.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.style.TextOverflow
import com.fvanaldewereld.rpgcompanion.ui.home.model.HomeScreenAction.BottomNavBarAction

@Composable
internal fun HomeScreenBottomNavBar(onBottomNavBarAction: (BottomNavBarAction) -> Unit) {
    val bottomNavBarItems = listOf(
        HomeBottomNavBarItem(
            label = "Scenarios",
            imageVector = Icons.Default.Create,
            onClick = { onBottomNavBarAction(BottomNavBarAction.ScenariosPressed) },
        ),
        HomeBottomNavBarItem(
            label = "Library",
            imageVector = Icons.Default.Menu,
            onClick = { onBottomNavBarAction(BottomNavBarAction.LibraryPressed) },
            enabled = false,
        ),
        HomeBottomNavBarItem(
            label = "Home",
            imageVector = Icons.Default.Home,
            onClick = { onBottomNavBarAction(BottomNavBarAction.HomePressed) },
        ),
        HomeBottomNavBarItem(
            label = "Games",
            imageVector = Icons.Default.Favorite,
            onClick = { onBottomNavBarAction(BottomNavBarAction.GamesPressed) },
            enabled = false,
        ),
        HomeBottomNavBarItem(
            label = "Characters",
            imageVector = Icons.Default.Face,
            onClick = { onBottomNavBarAction(BottomNavBarAction.CharactersPressed) },
            enabled = false,
        ),
    )
    NavigationBar {
        var selectedId = remember { 2 }
        bottomNavBarItems.onEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(item.imageVector, contentDescription = item.label) },
                label = { Text(item.label, overflow = TextOverflow.Ellipsis, maxLines = 1) },
                selected = selectedId == index,
                onClick = {
                    selectedId = index
                    item.onClick()
                },
                enabled = item.enabled,
            )
        }
    }
}
