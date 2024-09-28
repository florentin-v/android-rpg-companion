package com.fvanaldewereld.rpgcompanion.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.fvanaldewereld.rpgcompanion.common.navigation.NavigationRoute
import com.fvanaldewereld.rpgcompanion.common.navigation.animatedComposable
import com.fvanaldewereld.rpgcompanion.ui.home.component.HomeScreen
import com.fvanaldewereld.rpgcompanion.ui.home.model.HomeScreenAction.BottomNavBarAction
import com.fvanaldewereld.rpgcompanion.ui.home.model.HomeScreenAction.LastCharacterPressed
import com.fvanaldewereld.rpgcompanion.ui.home.model.HomeScreenAction.LastGameSessionPressed
import com.fvanaldewereld.rpgcompanion.ui.home.model.HomeScreenAction.LastScenarioPressed
import com.fvanaldewereld.rpgcompanion.ui.home.model.HomeScreenAction.TopAppBarAction
import com.fvanaldewereld.rpgcompanion.ui.scenario.detail.components.ScenarioDetailScreen
import com.fvanaldewereld.rpgcompanion.ui.scenario.list.components.ScenarioListScreen

@Composable
internal fun RPGCompanionNavigation() {
    val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = NavigationRoute.Home) {
        // This method navigates back in the navigation stack.
        fun navigateBack() = navHostController.navigateUp()

        // This method navigates to a specified route.
        fun navigateTo(route: NavigationRoute) = navHostController.navigate(route)

        animatedComposable<NavigationRoute.Home> {
            HomeScreen { homeScreenAction ->
                when (homeScreenAction) {
                    BottomNavBarAction.CharactersPressed -> {}
                    BottomNavBarAction.GamesPressed -> {}
                    BottomNavBarAction.HomePressed -> navigateTo(NavigationRoute.Home)
                    BottomNavBarAction.LibraryPressed -> {}
                    BottomNavBarAction.ScenariosPressed -> {
                        Log.d("DEBUG NAV", "--------------- ScenariosPressed")
                        navigateTo(NavigationRoute.ScenarioList)
                    }

                    is LastCharacterPressed -> {}
                    is LastGameSessionPressed -> {}
                    is LastScenarioPressed -> {
                        Log.d("DEBUG NAV", "--------------- LastScenarioPressed id : ${homeScreenAction.id}")

                        navigateTo(NavigationRoute.ScenarioDetail(scenarioId = homeScreenAction.id))
                    }

                    TopAppBarAction.ProfilePressed -> {}
                    TopAppBarAction.SearchPressed -> {}
                }
            }
        }

        animatedComposable<NavigationRoute.ScenarioList> {
            ScenarioListScreen(
                onBackButtonPressed = ::navigateBack,
                goToScenarioDetail = { scenarioId ->
                    navigateTo(NavigationRoute.ScenarioDetail(scenarioId = scenarioId))
                },
            )
        }

        animatedComposable<NavigationRoute.ScenarioDetail> {
            ScenarioDetailScreen(onBackButtonPressed = ::navigateBack)
        }
    }
}
