package com.fvanaldewereld.rpgcompanion.ui.home.components

import com.fvanaldewereld.rpgcompanion.common.ui.components.RPG_COMPANION_TOP_APP_BAR_TEST_TAG
import com.fvanaldewereld.rpgcompanion.common.ui.components.RpgCompanionTestTag

private const val HOME_TEST_TAG = "HOME"

sealed class HomeTestTag(prefix: String = "", suffix: String = "") :
    RpgCompanionTestTag(HOME_TEST_TAG, prefix, suffix) {
    data object Button : HomeTestTag(suffix = "Button")
    data object Screen : HomeTestTag(suffix = "SCREEN")
    data object TopAppBar : HomeTestTag(suffix = RPG_COMPANION_TOP_APP_BAR_TEST_TAG)
}
