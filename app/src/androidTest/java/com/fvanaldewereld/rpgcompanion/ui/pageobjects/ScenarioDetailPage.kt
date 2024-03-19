package com.fvanaldewereld.rpgcompanion.ui.pageobjects

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import com.fvanaldewereld.rpgcompanion.ui.scenario.detail.components.ScenarioDetailTestTag


class ScenarioDetailPage(private val composeTestRule: ComposeContentTestRule) {
    private val topAppBar = composeTestRule.onNodeWithTag(ScenarioDetailTestTag.TopAppBar.value)
    private val screen = composeTestRule.onNodeWithTag(ScenarioDetailTestTag.Screen.value)
    private val bottomBar = composeTestRule.onNodeWithTag(ScenarioDetailTestTag.BottomBar.value)

    fun assertScreenIsDisplayed() {
        composeTestRule.onRoot(true).printToLog("SCENARIO_DETAIL_PAGE")
        screen.assertIsDisplayed()
    }

    fun assertTopAppBarIsDisplayed() {
        topAppBar.assertIsDisplayed()
    }

    fun assertBottomBarIsDisplayed() {
        bottomBar.assertIsDisplayed()
    }
}
