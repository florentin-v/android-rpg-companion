package com.fvanaldewereld.rpgcompanion.ui.scenario.detail.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ScenarioModel
import com.fvanaldewereld.rpgcompanion.common.navigation.NavigationRoute
import com.fvanaldewereld.rpgcompanion.common.navigation.animatedComposable
import com.fvanaldewereld.rpgcompanion.common.ui.components.RpgCompanionBottomBar
import com.fvanaldewereld.rpgcompanion.common.ui.components.RpgCompanionTopAppBar
import com.fvanaldewereld.rpgcompanion.common.ui.models.BottomNavigationBarItemInfo
import com.fvanaldewereld.rpgcompanion.common.ui.theme.RpgCompanionTheme
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory
import com.fvanaldewereld.rpgcompanion.ui.scenario.detail.R
import kotlinx.collections.immutable.persistentMapOf

@Composable
internal fun ScenarioDetailSuccess(
    scenario: ScenarioModel,
    onBackButtonPressed: () -> Unit = {},
) {
    val bottomBarItems = persistentMapOf(
        NavigationRoute.ScenarioDetail.Basics to BottomNavigationBarItemInfo(
            labelResId = R.string.scenarioDetail_page_basics,
            imageVector = Icons.Filled.Info,
        ),
        NavigationRoute.ScenarioDetail.Chapters to BottomNavigationBarItemInfo(
            labelResId = R.string.scenarioDetail_page_chapters,
            imageVector = Icons.AutoMirrored.Filled.List,
        ),
        NavigationRoute.ScenarioDetail.Characters to BottomNavigationBarItemInfo(
            labelResId = R.string.scenarioDetail_page_characters,
            imageVector = Icons.Filled.Person,
        ),
        NavigationRoute.ScenarioDetail.Places to BottomNavigationBarItemInfo(
            labelResId = R.string.scenarioDetail_page_places,
            imageVector = Icons.Filled.LocationOn,
        ),
    )
    var selectedItemIndex by remember { mutableIntStateOf(0) }
    val navHostController: NavHostController = rememberNavController()

    // This method navigates to a specified route (NavigationRoute).
    fun navigateTo(navRoute: NavigationRoute) = navHostController.navigate(navRoute.route)

    Scaffold(
        topBar = {
            RpgCompanionTopAppBar(
                title = scenario.mainInfo.title.value ?: stringResource(id = R.string.scenarioDetail_page_noTitle),
                onBackButtonPressed = onBackButtonPressed,
            )
        },
        bottomBar = {
            RpgCompanionBottomBar(
                bottomBarItems = bottomBarItems,
                isSelected = { index ->
                    selectedItemIndex == index
                },
                onItemClick = { index, navRoute ->
                    selectedItemIndex = index
                    navigateTo(navRoute = navRoute)
                },
            )
        },
    ) {
        NavHost(
            navController = navHostController,
            startDestination = NavigationRoute.ScenarioDetail.Basics.route,
            modifier = Modifier.padding(it),
        ) {
            animatedComposable(route = NavigationRoute.ScenarioDetail.Basics.route) {
                ScenarioDetailMainInfo(scenario.mainInfo)
            }
            animatedComposable(route = NavigationRoute.ScenarioDetail.Places.route) {
                ScenarioDetailPlaces(scenario.places)
            }
            animatedComposable(route = NavigationRoute.ScenarioDetail.Chapters.route) {
                ScenarioDetailChapters(scenario.chapters)
            }
            animatedComposable(route = NavigationRoute.ScenarioDetail.Characters.route) {
                ScenarioDetailCharacters(scenario.characters)
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
