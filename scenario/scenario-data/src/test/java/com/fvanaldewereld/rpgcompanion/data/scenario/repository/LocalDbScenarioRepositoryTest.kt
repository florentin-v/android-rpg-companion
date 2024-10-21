package com.fvanaldewereld.rpgcompanion.data.scenario.repository

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.repositories.DbScenarioRepository
import com.fvanaldewereld.rpgcompanion.data.scenario.mapper.ScenarioMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.dao.ScenarioDao
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDbObjectMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory
import com.fvanaldewereld.rpgcompanion.test.common.BasicKoinTest
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.KoinApplication
import org.koin.dsl.module
import org.koin.test.inject
import org.mockito.internal.verification.VerificationModeFactory.times
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class LocalDbScenarioRepositoryTest : BasicKoinTest() {

    private val mockScenarioDao by inject<ScenarioDao>()
    private val mockScenarioMapper by inject<ScenarioMapper>()

    private lateinit var localDbScenarioRepository: DbScenarioRepository

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { mock<ScenarioDao>() }
                single { mock<ScenarioMapper>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        localDbScenarioRepository = LocalDbScenarioRepository(
            scenarioDao = mockScenarioDao,
            scenarioMapper = mockScenarioMapper,
        )
    }

    @Test
    fun `GIVEN mock ScenarioMapper and insertScenario WHEN executing addScenario of LocalDbScenarioRepository THEN return Long`() =
        runBlocking {
            // GIVEN
            whenever(mockScenarioMapper.toDataLocalDb(ScenarioModelMockFactory.scenarioModelWithoutId))
                .thenReturn(ScenarioDbObjectMockFactory.scenarioLocalDb)
            whenever(mockScenarioDao.insertScenario(ScenarioDbObjectMockFactory.scenarioLocalDb))
                .thenReturn(ScenarioDbObjectMockFactory.SCENARIO_ID)

            // WHEN
            val result = localDbScenarioRepository.addScenario(ScenarioModelMockFactory.scenarioModelWithoutId)

            // THEN
            verify(mockScenarioDao, times(1))
                .insertScenario(ScenarioDbObjectMockFactory.scenarioLocalDb)
            assertEquals(
                actual = result,
                expected = ScenarioModelMockFactory.SCENARIO_ID,
            )
        }

    @Test
    fun `GIVEN mock ScenarioMapper and getAllScenarios WHEN executing getAllScenarios of LocalDbScenarioRepository THEN return list of Scenario`() =
        runBlocking {
            // GIVEN
            whenever(mockScenarioMapper.toDomain(ScenarioDbObjectMockFactory.scenarioLocalDb))
                .thenReturn(ScenarioModelMockFactory.scenarioModelWithId)
            whenever(mockScenarioDao.getAllScenarioList())
                .thenReturn(listOf(ScenarioDbObjectMockFactory.scenarioLocalDb))

            // WHEN
            val result = localDbScenarioRepository.getAllScenarioList()

            // THEN
            verify(mockScenarioDao, times(1))
                .getAllScenarioList()
            assertEquals(
                actual = result,
                expected = listOf(ScenarioModelMockFactory.scenarioModelWithId),
            )
        }

    @Test
    fun `GIVEN mock ScenarioMapper and getScenarioByDocumentName WHEN executing getScenarioByDocumentName of LocalDbScenarioRepository THEN return list of Scenario`() =
        runBlocking {
            // GIVEN
            whenever(mockScenarioMapper.toDomain(ScenarioDbObjectMockFactory.scenarioLocalDb))
                .thenReturn(ScenarioModelMockFactory.scenarioModelWithId)
            whenever(mockScenarioDao.getScenarioByDocumentName(ScenarioDbObjectMockFactory.SCENARIO_DOCUMENT_NAME))
                .thenReturn(ScenarioDbObjectMockFactory.scenarioLocalDb)

            // WHEN
            val result = localDbScenarioRepository.getScenarioByDocumentName(
                ScenarioDbObjectMockFactory.SCENARIO_DOCUMENT_NAME,
            )

            // THEN
            verify(mockScenarioDao, times(1))
                .getScenarioByDocumentName(ScenarioDbObjectMockFactory.SCENARIO_DOCUMENT_NAME)
            assertEquals(
                actual = result,
                expected = ScenarioModelMockFactory.scenarioModelWithId,
            )
        }

    @Test
    fun `GIVEN mock ScenarioMapper and getScenarioById WHEN executing getScenarioById of LocalDbScenarioRepository THEN return list of Scenario`() =
        runBlocking {
            // GIVEN
            whenever(mockScenarioMapper.toDomain(ScenarioDbObjectMockFactory.scenarioLocalDb))
                .thenReturn(ScenarioModelMockFactory.scenarioModelWithId)
            whenever(mockScenarioDao.getScenarioById(ScenarioDbObjectMockFactory.SCENARIO_ID))
                .thenReturn(ScenarioDbObjectMockFactory.scenarioLocalDb)

            // WHEN
            val result =
                localDbScenarioRepository.getScenarioById(ScenarioModelMockFactory.SCENARIO_ID)

            // THEN
            verify(mockScenarioDao, times(1))
                .getScenarioById(ScenarioDbObjectMockFactory.SCENARIO_ID)
            assertEquals(
                actual = result,
                expected = ScenarioModelMockFactory.scenarioModelWithId,
            )
        }

    @Test
    fun `GIVEN mock ScenarioMapper and deleteById WHEN executing deleteById of LocalDbScenarioRepository THEN return list of Scenario`() =
        runBlocking {
            // GIVEN
            whenever(mockScenarioDao.getScenarioById(ScenarioDbObjectMockFactory.SCENARIO_ID))
                .thenReturn(ScenarioDbObjectMockFactory.scenarioLocalDb)
            whenever(mockScenarioDao.deleteScenario(ScenarioDbObjectMockFactory.scenarioLocalDb))
                .thenReturn(ScenarioDbObjectMockFactory.SCENARIO_ID)

            // WHEN
            val result = localDbScenarioRepository.deleteById(ScenarioModelMockFactory.SCENARIO_ID)

            // THEN
            verify(mockScenarioDao, times(1))
                .getScenarioById(ScenarioDbObjectMockFactory.SCENARIO_ID)
            verify(mockScenarioDao, times(1))
                .deleteScenario(ScenarioDbObjectMockFactory.scenarioLocalDb)
            assertEquals(
                actual = result,
                expected = ScenarioModelMockFactory.SCENARIO_ID,
            )
        }
}
