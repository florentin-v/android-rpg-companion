package com.fvanaldewereld.rpgcompanion.common.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.fvanaldewereld.rpgcompanion.common.navigation.NavigationRoute
import com.fvanaldewereld.rpgcompanion.ui.scenario.detail.components.BottomNavigationBarItemInfo
import kotlinx.collections.immutable.ImmutableMap

@Composable
fun RpgCompanionBottomBar(
    bottomBarItems: ImmutableMap<NavigationRoute, BottomNavigationBarItemInfo>,
    isSelected: (Int) -> Boolean,
    onItemClick: (Int, NavigationRoute) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(modifier = modifier) {
        bottomBarItems.onEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(item.value.imageVector, contentDescription = item.key.route) },
                label = { Text(stringResource(id = item.value.labelResId)) },
                selected = isSelected(index),
                onClick = { onItemClick(index, item.key) },
            )
        }
    }
}
