package com.fvanaldewereld.rpgcompanion.common.navigation

import kotlinx.serialization.Serializable

interface NavigationRoute {
    @Serializable
    data object Home : NavigationRoute

    @Serializable
    data object ScenarioList : NavigationRoute

    @Serializable
    data class ScenarioDetail(val scenarioId: Long) : NavigationRoute

    interface SubScenarioDetail {

        @Serializable
        data object Basics : NavigationRoute

        @Serializable
        data object Chapters : NavigationRoute

        @Serializable
        data object Characters : NavigationRoute

        @Serializable
        data object Places : NavigationRoute
    }
}
