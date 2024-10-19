package com.fvanaldewereld.rpgcompanion.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
import com.fvanaldewereld.rpgcompanion.ui.home.viewModel.HomeViewModel
import com.fvanaldewereld.rpgcompanion.ui.scenario.detail.components.ScenarioDetailScreen
import com.fvanaldewereld.rpgcompanion.ui.scenario.list.components.ScenarioListScreen
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun RPGCompanionNavigation() {
    val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = NavigationRoute.Home) {
        // This method navigates back in the navigation stack.
        fun navigateBack() = navHostController.navigateUp()

        // This method navigates to a specified route.
        fun navigateTo(route: NavigationRoute) = navHostController.navigate(route)

        animatedComposable<NavigationRoute.Home> {
            val viewModel: HomeViewModel = koinViewModel()
            HomeScreen(
                uiState = viewModel.homeUIStateFlow.collectAsStateWithLifecycle().value,
            ) { homeScreenAction ->
                when (homeScreenAction) {
                    BottomNavBarAction.CharactersPressed -> {}
                    BottomNavBarAction.GamesPressed -> {}
                    BottomNavBarAction.HomePressed -> navigateTo(
                        route = NavigationRoute.Home,
                    )

                    BottomNavBarAction.LibraryPressed -> {}
                    BottomNavBarAction.ScenariosPressed -> navigateTo(
                        route = NavigationRoute.ScenarioList,
                    )

                    is LastCharacterPressed -> {}
                    is LastGameSessionPressed -> {}
                    is LastScenarioPressed -> navigateTo(
                        route = NavigationRoute.ScenarioDetail(
                            scenarioId = homeScreenAction.id,
                        ),
                    )

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
