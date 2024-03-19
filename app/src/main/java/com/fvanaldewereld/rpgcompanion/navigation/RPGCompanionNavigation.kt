package com.fvanaldewereld.rpgcompanion.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.fvanaldewereld.rpgcompanion.common.navigation.NavigationRoute
import com.fvanaldewereld.rpgcompanion.common.navigation.animatedComposable
import com.fvanaldewereld.rpgcompanion.ui.home.components.HomeScreen
import com.fvanaldewereld.rpgcompanion.ui.scenario.detail.components.ScenarioDetailScreen
import com.fvanaldewereld.rpgcompanion.ui.scenario.list.components.ScenarioListScreen

@Composable
internal fun RPGCompanionNavigation(navHostController: NavHostController = rememberNavController()) {

    NavHost(navController = navHostController, startDestination = NavigationRoute.Home.route) {
        // This method navigates back in the navigation stack.
        fun navigateBack() = navHostController.navigateUp()

        // This method navigates to a specified route (NavigationRoute).
        fun navigateTo(navRoute: NavigationRoute) = navHostController.navigate(navRoute.route)

        // This method navigates to a specified route (String).
        fun navigateTo(navRoute: String) = navHostController.navigate(navRoute)

        animatedComposable(route = NavigationRoute.Home.route) {
            HomeScreen {
                navigateTo(NavigationRoute.ScenarioList)
            }
        }

        animatedComposable(route = NavigationRoute.ScenarioList.route) {
            ScenarioListScreen(
                onBackButtonPressed = ::navigateBack,
                goToScenarioDetail = { scenarioId ->
                    navigateTo(NavigationRoute.ScenarioDetail.createRoute(scenarioId = scenarioId))
                },
            )
        }

        animatedComposable(route = NavigationRoute.ScenarioDetail.route) {
            ScenarioDetailScreen(onBackButtonPressed = ::navigateBack)
        }
    }
}
