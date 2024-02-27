package com.fvanaldewereld.rpgcompanion.data.scenario.repositories

import BasicKoinTest
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.repositories.DbScenarioRepository
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.dbObjectMappers.ScenarioMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.dao.ScenarioDao
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDbObjectMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.KoinApplication
import org.koin.dsl.module
import org.koin.test.inject
import org.mockito.Mockito
import org.mockito.internal.verification.VerificationModeFactory.times

class LocalDbScenarioRepositoryTest : BasicKoinTest() {

    private val mockScenarioDao by inject<ScenarioDao>()
    private val mockScenarioMapper by inject<ScenarioMapper>()
    private lateinit var localDbScenarioRepository: DbScenarioRepository

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { Mockito.mock<ScenarioDao>() }
                single { Mockito.mock<ScenarioMapper>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        localDbScenarioRepository = LocalDbScenarioRepositoryImpl()
    }

    @Test
    fun `GIVEN mock ScenarioMapper and insertScenario WHEN executing addScenario of LocalDbScenarioRepository THEN return Long`() =
        runBlocking {
            // GIVEN
            Mockito.`when`(mockScenarioMapper.from(ScenarioModelMockFactory.scenarioModelWithoutId))
                .thenReturn(ScenarioDbObjectMockFactory.scenario)
            Mockito.`when`(mockScenarioDao.insertScenario(ScenarioDbObjectMockFactory.scenario))
                .thenReturn(ScenarioDbObjectMockFactory.SCENARIO_ID)

            // WHEN
            val result =
                localDbScenarioRepository.addScenario(ScenarioModelMockFactory.scenarioModelWithoutId)

            // THEN
            Mockito.verify(mockScenarioDao, times(1))
                .insertScenario(ScenarioDbObjectMockFactory.scenario)
            Assertions.assertEquals(result, ScenarioModelMockFactory.SCENARIO_ID)
        }

    @Test
    fun `GIVEN mock ScenarioMapper and getAllScenarios WHEN executing getAllScenarios of LocalDbScenarioRepository THEN return list of Scenario`() =
        runBlocking {
            // GIVEN
            Mockito.`when`(mockScenarioMapper.to(ScenarioDbObjectMockFactory.scenario))
                .thenReturn(ScenarioModelMockFactory.scenarioModelWithId)
            Mockito.`when`(mockScenarioDao.getAllScenarios())
                .thenReturn(listOf(ScenarioDbObjectMockFactory.scenario))

            // WHEN
            val result = localDbScenarioRepository.getAllScenarios()

            // THEN
            Mockito.verify(mockScenarioDao, times(1)).getAllScenarios()
            Assertions.assertEquals(result, listOf(ScenarioModelMockFactory.scenarioModelWithId))
        }

    @Test
    fun `GIVEN mock ScenarioMapper and getScenarioByDocumentName WHEN executing getScenarioByDocumentName of LocalDbScenarioRepository THEN return list of Scenario`() =
        runBlocking {
            // GIVEN
            Mockito.`when`(mockScenarioMapper.to(ScenarioDbObjectMockFactory.scenario))
                .thenReturn(ScenarioModelMockFactory.scenarioModelWithId)
            Mockito.`when`(
                mockScenarioDao.getScenarioByDocumentName(
                    documentName = ScenarioDbObjectMockFactory.SCENARIO_DOCUMENT_NAME,
                ),
            ).thenReturn(ScenarioDbObjectMockFactory.scenario)

            // WHEN
            val result =
                localDbScenarioRepository.getScenarioByDocumentName(ScenarioDbObjectMockFactory.SCENARIO_DOCUMENT_NAME)

            // THEN
            Mockito.verify(mockScenarioDao, times(1))
                .getScenarioByDocumentName(ScenarioDbObjectMockFactory.SCENARIO_DOCUMENT_NAME)
            Assertions.assertEquals(result, ScenarioModelMockFactory.scenarioModelWithId)
        }

    @Test
    fun `GIVEN mock ScenarioMapper and getScenarioById WHEN executing getScenarioById of LocalDbScenarioRepository THEN return list of Scenario`() =
        runBlocking {
            // GIVEN
            Mockito.`when`(mockScenarioMapper.to(ScenarioDbObjectMockFactory.scenario))
                .thenReturn(ScenarioModelMockFactory.scenarioModelWithId)
            Mockito.`when`(mockScenarioDao.getScenarioById(ScenarioDbObjectMockFactory.SCENARIO_ID))
                .thenReturn(ScenarioDbObjectMockFactory.scenario)

            // WHEN
            val result =
                localDbScenarioRepository.getScenarioById(ScenarioModelMockFactory.SCENARIO_ID)

            // THEN
            Mockito.verify(mockScenarioDao, times(1))
                .getScenarioById(ScenarioDbObjectMockFactory.SCENARIO_ID)
            Assertions.assertEquals(result, ScenarioModelMockFactory.scenarioModelWithId)
        }

    @Test
    fun `GIVEN mock ScenarioMapper and deleteById WHEN executing deleteById of LocalDbScenarioRepository THEN return list of Scenario`() =
        runBlocking {
            // GIVEN
            Mockito.`when`(mockScenarioDao.getScenarioById(ScenarioDbObjectMockFactory.SCENARIO_ID))
                .thenReturn(ScenarioDbObjectMockFactory.scenario)
            Mockito.`when`(mockScenarioDao.deleteScenario(ScenarioDbObjectMockFactory.scenario))
                .thenReturn(ScenarioDbObjectMockFactory.SCENARIO_ID)

            // WHEN
            val result = localDbScenarioRepository.deleteById(ScenarioModelMockFactory.SCENARIO_ID)

            // THEN
            Mockito.verify(mockScenarioDao, times(1))
                .getScenarioById(ScenarioDbObjectMockFactory.SCENARIO_ID)
            Mockito.verify(mockScenarioDao, times(1))
                .deleteScenario(ScenarioDbObjectMockFactory.scenario)
            Assertions.assertEquals(result, ScenarioModelMockFactory.SCENARIO_ID)
        }
}
