package com.fvanaldewereld.rpgcompanion.ui.pageobjects

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.fvanaldewereld.rpgcompanion.ui.home.components.HomeTestTag

class HomePage(composeTestRule: ComposeContentTestRule) {
    private val button = composeTestRule.onNodeWithTag(HomeTestTag.Button.value)
    private val screen = composeTestRule.onNodeWithTag(HomeTestTag.Screen.value)
    private val topAppBar = composeTestRule.onNodeWithTag(HomeTestTag.TopAppBar.value)

    fun assertScreenIsDisplayed() {
        topAppBar.assertIsDisplayed()
    }

    fun assertTopAppBarIsDisplayed() {
        topAppBar.assertIsDisplayed()
    }

    fun clickOnButton() {
        button.performClick()
    }
}
