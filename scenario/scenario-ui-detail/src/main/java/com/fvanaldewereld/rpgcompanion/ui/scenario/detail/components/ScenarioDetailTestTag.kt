package com.fvanaldewereld.rpgcompanion.ui.scenario.detail.components

import com.fvanaldewereld.rpgcompanion.common.ui.components.RPG_COMPANION_BOTTOM_BAR_TEST_TAG
import com.fvanaldewereld.rpgcompanion.common.ui.components.RPG_COMPANION_TOP_APP_BAR_TEST_TAG
import com.fvanaldewereld.rpgcompanion.common.ui.components.RpgCompanionTestTag

private const val SCENARIO_DETAIL_TEST_TAG = "SCENARIO_DETAIL"

sealed class ScenarioDetailTestTag(prefix: String = "", suffix: String = "") :
    RpgCompanionTestTag(SCENARIO_DETAIL_TEST_TAG, prefix, suffix) {

    data object BottomBar : ScenarioDetailTestTag(suffix = RPG_COMPANION_BOTTOM_BAR_TEST_TAG)
    data object Screen : ScenarioDetailTestTag(suffix = "SCREEN")
    data object TopAppBar : ScenarioDetailTestTag(suffix = RPG_COMPANION_TOP_APP_BAR_TEST_TAG)

}
