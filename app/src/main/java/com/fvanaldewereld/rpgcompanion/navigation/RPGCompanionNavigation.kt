package com.fvanaldewereld.rpgcompanion.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.fvanaldewereld.rpgcompanion.ui.components.home.HomeScreen
import com.fvanaldewereld.rpgcompanion.ui.scenario.detail.components.ScenarioDetailScreen
import com.fvanaldewereld.rpgcompanion.ui.scenario.list.components.ScenarioListScreen
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun RPGCompanionNavigation() {
    val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = NavigationRoute.Home.route) {
        // This method navigates back in the navigation stack.
        fun navigateBack() = navHostController.navigateUp()

        // This method navigates to a specified route (NavigationRoute).
        fun navigateTo(navRoute: NavigationRoute) = navHostController.navigate(navRoute.route)

        // This method navigates to a specified route (String).
        fun navigateTo(navRoute: String) = navHostController.navigate(navRoute)

        // This method navigates to a route while popping all destinations up to a specified route
        fun navigatePopAllTo(navRoute: NavigationRoute, navRouteToPopUpTo: NavigationRoute) {
            navHostController.navigate(navRoute.route) {
                // Configure popping behavior
                popUpTo(navRouteToPopUpTo.route) { inclusive = true }
            }
        }

        animatedComposable(
            route = NavigationRoute.Home.route,
            deepLinks = NavigationRoute.Home.deepLinks,
        ) {
            HomeScreen {
                navigateTo(NavigationRoute.ScenarioList)
            }
        }

        animatedComposable(
            route = NavigationRoute.ScenarioList.route,
            deepLinks = NavigationRoute.ScenarioList.deepLinks,
        ) {
            ScenarioListScreen(
                viewModel = koinViewModel(),
                onBackButtonPressed = ::navigateBack,
                goToScenarioDetail = { scenarioId ->
                    navigateTo(
                        NavigationRoute.ScenarioDetail.createRoute(scenarioId = scenarioId),
                    )
                },
            )
        }

        animatedComposable(
            route = NavigationRoute.ScenarioDetail.route,
            deepLinks = NavigationRoute.ScenarioDetail.deepLinks,
        ) {
            ScenarioDetailScreen(
                viewModel = koinViewModel(),
                onBackButtonPressed = ::navigateBack,
            )
        }
    }
}
