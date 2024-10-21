package com.fvanaldewereld.rpgcompanion.lib.domain.scenario

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.repositories.GoogleDocsRepository
import com.fvanaldewereld.rpgcompanion.lib.domain.scenario.useCase.GetScenarioByUrlUseCase
import com.fvanaldewereld.rpgcompanion.mockFactory.GoogleDocsMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory
import com.fvanaldewereld.rpgcompanion.test.common.BasicKoinTest
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.KoinApplication
import org.koin.dsl.module
import org.koin.test.inject
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class GetScenarioByUrlUseCaseTest : BasicKoinTest() {

    private val mockGoogleDocsRepository by inject<GoogleDocsRepository>()
    private lateinit var getScenarioByUrlUseCase: GetScenarioByUrlUseCase

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { mock<GoogleDocsRepository>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        getScenarioByUrlUseCase = GetScenarioByUrlUseCase(
            googleDocsRepository = mockGoogleDocsRepository,
        )
    }

    @Test
    fun `WHEN executing GetGdocsByUrlUseCase THEN return ScenarioModel`() =
        runBlocking {
            // GIVEN
            whenever(mockGoogleDocsRepository.getScenarioByGdocsUrl(GoogleDocsMockFactory.googleDocsUrl))
                .thenReturn(ScenarioModelMockFactory.scenarioModelWithoutId)

            // WHEN
            val scenario = getScenarioByUrlUseCase(GoogleDocsMockFactory.googleDocsUrl)

            // THEN
            assertEquals(
                actual = scenario,
                expected = ScenarioModelMockFactory.scenarioModelWithoutId,
            )
        }
}
