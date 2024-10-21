package com.fvanaldewereld.rpgcompanion.ui.home.component

import androidx.compose.ui.graphics.vector.ImageVector

data class HomeBottomNavBarItem(
    val label: String,
    val imageVector: ImageVector,
    val onClick: () -> Unit,
    val enabled: Boolean = true,
)
