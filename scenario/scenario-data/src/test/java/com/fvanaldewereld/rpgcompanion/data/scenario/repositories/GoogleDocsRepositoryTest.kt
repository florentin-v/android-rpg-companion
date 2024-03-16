package com.fvanaldewereld.rpgcompanion.data.scenario.repositories

import BasicKoinTest
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.repositories.GoogleDocsRepository
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers.ScenarioModelMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.googleDocs.GoogleDocsDataSource
import com.fvanaldewereld.rpgcompanion.mockFactory.GoogleDocsMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDtoMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.KoinApplication
import org.koin.dsl.module
import org.koin.test.inject
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GoogleDocsRepositoryTest : BasicKoinTest() {

    private val mockGoogleDocsDataSource by inject<GoogleDocsDataSource>()
    private val mockScenarioModelMapper by inject<ScenarioModelMapper>()
    private lateinit var googleDocsRepository: GoogleDocsRepository

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { mock<GoogleDocsDataSource>() }
                single { mock<ScenarioModelMapper>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        googleDocsRepository = GoogleDocsRepositoryImpl()
    }

    @Test
    fun `GIVEN mock getGoogleDocsById and ScenarioModelMapper WHEN executing getScenarioByGdocsUrl of GoogleDocsRepository THEN return Document`() =
        runBlocking {
            // GIVEN
            whenever(mockGoogleDocsDataSource.getGoogleDocsById(GoogleDocsMockFactory.GOOGLE_DOCS_DOCUMENT_ID))
                .thenReturn(ScenarioDtoMockFactory.scenarioDto)
            whenever(mockScenarioModelMapper.to(ScenarioDtoMockFactory.scenarioDto))
                .thenReturn(ScenarioModelMockFactory.scenarioModelWithoutId)

            // WHEN
            val scenarioModel = googleDocsRepository.getScenarioByGdocsUrl(GoogleDocsMockFactory.googleDocsUrl)

            // THEN
            Assertions.assertEquals(scenarioModel, ScenarioModelMockFactory.scenarioModelWithoutId)
        }
}
