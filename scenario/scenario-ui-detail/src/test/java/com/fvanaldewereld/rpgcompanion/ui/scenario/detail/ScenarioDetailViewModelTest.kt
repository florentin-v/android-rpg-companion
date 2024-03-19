@file:OptIn(ExperimentalCoroutinesApi::class)

package com.fvanaldewereld.rpgcompanion.ui.scenario.detail

import BasicKoinTest
import KTestDispatchersImpl
import androidx.lifecycle.SavedStateHandle
import com.fvanaldewereld.rpgcompanion.common.dispatchers.KDispatchers
import com.fvanaldewereld.rpgcompanion.common.navigation.SCENARIO_ID_KEY
import com.fvanaldewereld.rpgcompanion.lib.domain.scenario.usecases.GetScenarioByDocumentNameUseCase
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.KoinApplication
import org.koin.dsl.module
import org.koin.test.inject
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub

class ScenarioDetailViewModelTest : BasicKoinTest() {

    private val mockGetScenarioByDocumentNameUseCase by inject<GetScenarioByDocumentNameUseCase>()

    private lateinit var scenarioDetailViewModel: ScenarioDetailViewModel

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single {
                    mock<GetScenarioByDocumentNameUseCase>()
                }
                single<KDispatchers> { KTestDispatchersImpl() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        mockGetScenarioByDocumentNameUseCase.stub {
            onBlocking { invoke(any()) }.doReturn(
                ScenarioModelMockFactory.scenarioModelWithId,
            )
        }
    }

    @Test
    fun `GIVEN scenarioId in SavedStateHandle WHEN init ScenarioDetailViewModel THEN scenarioDetailUiStateFlow is Success with loaded scenario`() =
        runTest {
            // GIVEN
            val testDispatcher = UnconfinedTestDispatcher(testScheduler)
            Dispatchers.setMain(testDispatcher)

            val savedStateHandle = SavedStateHandle(mapOf(SCENARIO_ID_KEY to "1"))

            // WHEN
            scenarioDetailViewModel = ScenarioDetailViewModel(savedStateHandle = savedStateHandle)

            // THEN
            Assertions.assertEquals(
                ScenarioDetailUiState.Success(scenario = ScenarioModelMockFactory.scenarioModelWithId),
                scenarioDetailViewModel.scenarioDetailUiStateFlow.value,
            )
        }
}
