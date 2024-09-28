package com.fvanaldewereld.rpgcompanion.ui.scenario.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ScenarioModel
import com.fvanaldewereld.rpgcompanion.common.navigation.NavigationRoute
import com.fvanaldewereld.rpgcompanion.common.ui.components.RpgCompanionTopAppBar
import com.fvanaldewereld.rpgcompanion.common.ui.theme.RpgCompanionTheme
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory
import com.fvanaldewereld.rpgcompanion.ui.scenario.detail.R
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ScenarioDetailSuccess(
    scenario: ScenarioModel,
    onBackButtonPressed: () -> Unit = {},
) {
    val tabs = persistentListOf(
        TabInfo(
            labelResId = R.string.scenarioDetail_page_basics,
            imageVector = Icons.Filled.Info,
            route = NavigationRoute.SubScenarioDetail.Basics,
        ),
        TabInfo(
            labelResId = R.string.scenarioDetail_page_chapters,
            imageVector = Icons.AutoMirrored.Filled.List,
            route = NavigationRoute.SubScenarioDetail.Chapters,
        ),
        TabInfo(
            labelResId = R.string.scenarioDetail_page_characters,
            imageVector = Icons.Filled.Person,
            route = NavigationRoute.SubScenarioDetail.Characters,
        ),
        TabInfo(
            labelResId = R.string.scenarioDetail_page_places,
            imageVector = Icons.Filled.LocationOn,
            route = NavigationRoute.SubScenarioDetail.Places,
        ),
    )
    val scope = rememberCoroutineScope()
    val navHostController = rememberNavController()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { tabs.size })
    val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }

    // This method navigates to a specified route (NavigationRoute).
    fun navigateTo(route: NavigationRoute) = navHostController.navigate(route)

    Scaffold(
        topBar = {
            Column {
                RpgCompanionTopAppBar(
                    title = scenario.mainInfo.title.value ?: stringResource(id = R.string.scenarioDetail_page_noTitle),
                    onBackButtonPressed = onBackButtonPressed,
                )

                SecondaryTabRow(
                    selectedTabIndex = selectedTabIndex.value,
                ) {
                    tabs.forEachIndexed { index, tabInfo ->
                        Tab(
                            selected = selectedTabIndex.value == index,
                            icon = { Icon(tabInfo.imageVector, contentDescription = "") },
                            onClick = {
                                scope.launch {
                                    pagerState.scrollToPage(index)
                                }
                            },
                        )
                    }
                }
            }
        },
    ) {
        LaunchedEffect(pagerState) {
            snapshotFlow { pagerState.currentPage }.collect { page ->
                navigateTo(tabs[page].route)
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .padding(it)
                .fillMaxWidth(),
        ) {
            NavHost(
                navController = navHostController,
                startDestination = NavigationRoute.SubScenarioDetail.Basics,
            ) {
                composable<NavigationRoute.SubScenarioDetail.Basics> {
                    ScenarioDetailMainInfo(scenario.mainInfo)
                }
                composable<NavigationRoute.SubScenarioDetail.Places> {
                    ScenarioDetailPlaces(scenario.places)
                }
                composable<NavigationRoute.SubScenarioDetail.Chapters> {
                    ScenarioDetailChapters(scenario.chapters)
                }
                composable<NavigationRoute.SubScenarioDetail.Characters> {
                    ScenarioDetailCharacters(scenario.characters)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScenarioDetailSuccessPreview() {
    RpgCompanionTheme {
        ScenarioDetailSuccess(scenario = ScenarioModelMockFactory.scenarioModelWithoutId)
    }
}
