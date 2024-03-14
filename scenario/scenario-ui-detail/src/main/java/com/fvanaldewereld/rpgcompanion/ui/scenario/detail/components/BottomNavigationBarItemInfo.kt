package com.fvanaldewereld.rpgcompanion.ui.scenario.detail.components

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationBarItemInfo(
    @StringRes val labelResId: Int,
    val imageVector: ImageVector,
)
