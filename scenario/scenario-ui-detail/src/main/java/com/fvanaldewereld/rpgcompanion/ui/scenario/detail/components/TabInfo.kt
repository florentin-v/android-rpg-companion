package com.fvanaldewereld.rpgcompanion.ui.scenario.detail.components

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.fvanaldewereld.rpgcompanion.common.navigation.NavigationRoute

data class TabInfo(
    @StringRes val labelResId: Int,
    val imageVector: ImageVector,
    val route: NavigationRoute,
)
