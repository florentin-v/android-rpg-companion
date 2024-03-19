package com.fvanaldewereld.rpgcompanion.ui

import BaseTestSuite
import android.content.Context
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.dbObjectMappers.ScenarioMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.dbObjectMappers.ScenarioMapperImpl
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.ScenarioDatabase
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.dao.ScenarioDao
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.dao.ScenarioDaoImpl
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory.SCENARIO_TITLE
import com.fvanaldewereld.rpgcompanion.navigation.RPGCompanionNavigation
import com.fvanaldewereld.rpgcompanion.ui.pageobjects.HomePage
import com.fvanaldewereld.rpgcompanion.ui.pageobjects.ScenarioDetailPage
import com.fvanaldewereld.rpgcompanion.ui.pageobjects.ScenarioListPage
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.KoinApplication
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.kotlin.mock
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class RpgCompanionUiTests : BaseTestSuite(), KoinTest {

    private lateinit var mockScenarioDao: ScenarioDao
    private lateinit var mockScenarioDatabase: ScenarioDatabase
    private val mockScenarioMapper by inject<ScenarioMapper>()

    private val homePage = HomePage(composeTestRule)
    private val scenarioListPage = ScenarioListPage(composeTestRule)
    private val scenarioDetailPage = ScenarioDetailPage(composeTestRule)

    fun KoinApplication.buildModules() {
        modules(
            module {
                single {
                    mock<ScenarioMapper> { ScenarioMapperImpl() }
                }
            },
        )
    }

    @Before
    fun setUp() {
        composeTestRule.activity.setContent {
            navHostController = TestNavHostController(LocalContext.current)
            navHostController.navigatorProvider.addNavigator(ComposeNavigator())
            RPGCompanionNavigation(navHostController)
        }
    }

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        mockScenarioDatabase = Room.inMemoryDatabaseBuilder(
            context,
            ScenarioDatabase::class.java,
        ).build()
        mockScenarioDao = ScenarioDaoImpl()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        mockScenarioDatabase.close()
    }

    @Test
    fun `Check the empty Scenario List case`() {
        homePage.assertScreenIsDisplayed()
        homePage.assertTopAppBarIsDisplayed()
        homePage.clickOnButton()

        scenarioListPage.assertScreenIsDisplayed()
        scenarioListPage.assertTopAppBarIsDisplayed()
        scenarioListPage.assertNoResultIsDisplayed()
    }

    @Test
    fun `Check the standard case`() {
        val scenarioModel = ScenarioModelMockFactory.scenarioModelWithId
        val scenarioDbObject = mockScenarioMapper.from(to = scenarioModel)
        mockScenarioDao.insertScenario(scenarioDbObject)

        homePage.assertScreenIsDisplayed()
        homePage.assertTopAppBarIsDisplayed()
        homePage.clickOnButton()

        scenarioListPage.assertScreenIsDisplayed()
        scenarioListPage.assertTopAppBarIsDisplayed()
        scenarioListPage.assertItemIsDisplayed(index = 0)
        scenarioListPage.assertItemTitleIsDisplayed(index = 0)
        scenarioListPage.assertItemTitleHasGoodValue(index = 0, expectedValue = SCENARIO_TITLE)
        scenarioListPage.clickOnItemTitle(index = 0)
        scenarioDetailPage.assertScreenIsDisplayed()
        scenarioDetailPage.assertTopAppBarIsDisplayed()
        // WIP
    }

}
