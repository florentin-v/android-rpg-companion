package com.fvanaldewereld.rpgcompanion.navigation

import com.fvanaldewereld.rpgcompanion.ui.scenario.detail.ScenarioDetailViewModel.Companion.SCENARIO_ID_KEY

sealed class NavigationRoute(val route: String) {
    object Splash : NavigationRoute("splash")
    object Home : NavigationRoute("home")
    object ScenarioList : NavigationRoute("scenario_list")
    object ScenarioDetail : NavigationRoute("scenario_detail/{$SCENARIO_ID_KEY}") {
        fun createRoute(scenarioId: Long): String {
            return "scenario_detail/$scenarioId"
        }
    }
}
