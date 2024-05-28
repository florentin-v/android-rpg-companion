package com.fvanaldewereld.rpgcompanion.common.ui.models

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationBarItemInfo(
    @StringRes val labelResId: Int,
    val imageVector: ImageVector,
)
