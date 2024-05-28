package com.fvanaldewereld.rpgcompanion.ui.scenario.list.components

import com.fvanaldewereld.rpgcompanion.common.ui.components.RPG_COMPANION_TOP_APP_BAR_TEST_TAG
import com.fvanaldewereld.rpgcompanion.common.ui.components.RpgCompanionTestTag

private const val SCENARIO_LIST_TEST_TAG = "SCENARIO_LIST"

sealed class ScenarioListTestTag(prefix: String = "", suffix: String = "") :
    RpgCompanionTestTag(SCENARIO_LIST_TEST_TAG, prefix, suffix) {
    data object NoResult : ScenarioListTestTag(suffix = "NO_RESULT")
    data object Screen : ScenarioListTestTag(suffix = "SCREEN")
    data object TopAppBar : ScenarioListTestTag(suffix = RPG_COMPANION_TOP_APP_BAR_TEST_TAG)
    data class Item(val index: Int) : ScenarioListTestTag(suffix = "ITEM_$index")
    data class ItemTitle(val index: Int) : ScenarioListTestTag(suffix = "ITEM_TITLE_$index")
}
