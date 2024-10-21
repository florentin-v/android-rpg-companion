package com.fvanaldewereld.rpgcompanion.data.scenario.repository

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.repositories.GoogleDocsRepository
import com.fvanaldewereld.rpgcompanion.data.scenario.mapper.ScenarioMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.source.googleDocs.GoogleDocsDataSource
import com.fvanaldewereld.rpgcompanion.mockFactory.GoogleDocsMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDtoMockFactory
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

class GoogleDocsRepositoryTest : BasicKoinTest() {

    private val mockGoogleDocsDataSource by inject<GoogleDocsDataSource>()
    private val mockScenarioMapper by inject<ScenarioMapper>()
    private lateinit var googleDocsRepository: GoogleDocsRepository

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { mock<GoogleDocsDataSource>() }
                single { mock<ScenarioMapper>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        googleDocsRepository = GoogleDocsRepositoryImpl(
            googleDocsDataSource = mockGoogleDocsDataSource,
            scenarioMapper = mockScenarioMapper,
        )
    }

    @Test
    fun `GIVEN mock getGoogleDocsById and ScenarioModelMapper WHEN executing getScenarioByGdocsUrl of GoogleDocsRepository THEN return Document`() =
        runBlocking {
            // GIVEN
            whenever(mockGoogleDocsDataSource.getGoogleDocsById(GoogleDocsMockFactory.GOOGLE_DOCS_DOCUMENT_ID))
                .thenReturn(ScenarioDtoMockFactory.scenarioDto)
            whenever(mockScenarioMapper.toDomain(ScenarioDtoMockFactory.scenarioDto))
                .thenReturn(ScenarioModelMockFactory.scenarioModelWithoutId)

            // WHEN
            val scenarioModel = googleDocsRepository.getScenarioByGdocsUrl(GoogleDocsMockFactory.googleDocsUrl)

            // THEN
            assertEquals(
                actual = scenarioModel,
                expected = ScenarioModelMockFactory.scenarioModelWithoutId,
            )
        }
}
