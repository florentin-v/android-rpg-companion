package com.fvanaldewereld.rpgcompanion.ui.pageobjects

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.fvanaldewereld.rpgcompanion.ui.scenario.list.components.ScenarioListTestTag

class ScenarioListPage(private val composeTestRule: ComposeContentTestRule) {
    private val topAppBar = composeTestRule.onNodeWithTag(ScenarioListTestTag.TopAppBar.value)
    private val screen = composeTestRule.onNodeWithTag(ScenarioListTestTag.Screen.value)
    private val noResult = composeTestRule.onNodeWithTag(ScenarioListTestTag.NoResult.value)
    private fun item(index: Int) = composeTestRule.onNodeWithTag(ScenarioListTestTag.Item(index = index).value)
    private fun itemTitle(index: Int) =
        composeTestRule.onNodeWithTag(ScenarioListTestTag.ItemTitle(index = index).value, useUnmergedTree = true)

    fun assertItemTitleIsDisplayed(index: Int) {
        itemTitle(index).assertIsDisplayed()
    }

    fun assertItemTitleHasGoodValue(index: Int, expectedValue: String) {
        itemTitle(index).assertTextEquals(expectedValue)
    }

    fun assertItemIsDisplayed(index: Int) {
        item(index).assertIsDisplayed()
    }

    fun clickOnItemTitle(index: Int) {
        itemTitle(index).performClick()
    }

    fun assertScreenIsDisplayed() {
        screen.assertIsDisplayed()
    }

    fun assertTopAppBarIsDisplayed() {
        topAppBar.assertIsDisplayed()
    }

    fun assertNoResultIsDisplayed() {
        noResult.assertIsDisplayed()
    }
}
