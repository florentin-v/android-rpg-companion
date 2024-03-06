package com.fvanaldewereld.rpgcompanion.navigation

import androidx.navigation.NavDeepLink
import androidx.navigation.navDeepLink
import com.fvanaldewereld.rpgcompanion.ui.scenario.detail.ScenarioDetailViewModel.Companion.SCENARIO_ID_KEY

sealed class NavigationRoute(val route: String) {
    data object Home : NavigationRoute(route = "home")
    data object ScenarioList : NavigationRoute(route = "scenario-list")
    data object ScenarioDetail : NavigationRoute(route = "scenario-detail/{$SCENARIO_ID_KEY}") {
        fun createRoute(scenarioId: Long): String {
            return "scenario-detail/$scenarioId"
        }
    }

    val deepLinks: List<NavDeepLink>
        get() = listOf(
            navDeepLink {
                uriPattern = "app://rpgcompanion/$route"
            },
        )
}
