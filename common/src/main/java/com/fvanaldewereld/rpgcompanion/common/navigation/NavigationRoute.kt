package com.fvanaldewereld.rpgcompanion.common.navigation

const val SCENARIO_ID_KEY = "scenarioId"

sealed class NavigationRoute(val route: String) {
    data object Home : NavigationRoute("home")
    data object ScenarioList : NavigationRoute("scenario_list")
    data object ScenarioDetail : NavigationRoute("scenario_detail/{$SCENARIO_ID_KEY}") {
        fun createRoute(scenarioId: Long): String {
            return "scenario_detail/$scenarioId"
        }

        data object Basics : NavigationRoute("basics")
        data object Chapters : NavigationRoute("chapters")
        data object Characters : NavigationRoute("characters")
        data object Places : NavigationRoute("places")
    }
}
