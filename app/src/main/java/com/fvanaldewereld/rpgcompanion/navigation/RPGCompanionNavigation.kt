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
import com.fvanaldewereld.rpgcompanion.ui.scenario.detail.ScenarioDetailViewModel
import com.fvanaldewereld.rpgcompanion.ui.scenario.detail.component.ScenarioDetailScreen
import com.fvanaldewereld.rpgcompanion.ui.scenario.list.ScenarioListViewModel
import com.fvanaldewereld.rpgcompanion.ui.scenario.list.component.ScenarioListScreen
import com.fvanaldewereld.rpgcompanion.ui.scenario.list.model.ScenarioListScreenAction
import org.koin.androidx.compose.koinViewModel

@Composable
@Suppress("CyclomaticComplexMethod") // TODO FVA Rework to reduce complexity
internal fun RPGCompanionNavigation() {
    val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = NavigationRoute.Home) {
        // This method navigates back in the navigation stack.
        fun navigateBack() = navHostController.navigateUp()

        // This method navigates to a specified route.
        fun navigateTo(route: NavigationRoute) = navHostController.navigate(route)

        animatedComposable<NavigationRoute.Home> {
            val viewModel = koinViewModel<HomeViewModel>()

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
            val viewModel = koinViewModel<ScenarioListViewModel>()

            ScenarioListScreen(
                uiState = viewModel.uiStateFlow.collectAsStateWithLifecycle().value,
            ) { scenarioListScreenAction ->

                when (scenarioListScreenAction) {
                    is ScenarioListScreenAction.OnBackPressedButton -> navigateBack()

                    is ScenarioListScreenAction.AddScenario ->
                        viewModel.addScenario(
                            scenarioUrl = scenarioListScreenAction.url,
                            onSuccess = { scenarioId ->
                                navigateTo(
                                    route = NavigationRoute.ScenarioDetail(scenarioId = scenarioId),
                                )
                            },
                        )

                    is ScenarioListScreenAction.DeleteScenario ->
                        viewModel.deleteScenario(
                            scenarioId = scenarioListScreenAction.id,
                        )

                    is ScenarioListScreenAction.GoToScenarioDetail ->
                        navigateTo(
                            route = NavigationRoute.ScenarioDetail(
                                scenarioId = scenarioListScreenAction.id,
                            ),
                        )
                }
            }
        }

        animatedComposable<NavigationRoute.ScenarioDetail> {
            val viewModel = koinViewModel<ScenarioDetailViewModel>()

            ScenarioDetailScreen(
                uiState = viewModel.uiStateFlow.collectAsStateWithLifecycle().value,
                onBackButtonPressed = ::navigateBack,
            )
        }
    }
}
