package com.fvanaldewereld.rpgcompanion.data.scenario.mapper.localDb

import BasicKoinTest
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.InformationModel
import com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.embedded.InformationLocalDb
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDbObjectMockFactory
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

class ScenarioMapperTest : BasicKoinTest() {

    private val mockChapterMapper by inject<ChapterMapper>()
    private val mockCharacterMapper by inject<CharacterMapper>()
    private val mockPlaceMapper by inject<PlaceMapper>()
    private val mockInformationMapper by inject<InformationMapper>()

    private lateinit var scenarioMapper: ScenarioMapper

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { mock<ChapterMapper>() }
                single { mock<CharacterMapper>() }
                single { mock<PlaceMapper>() }
                single { mock<InformationMapper>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        scenarioMapper = ScenarioMapper(
            chapterMapper = mockChapterMapper,
            characterMapper = mockCharacterMapper,
            informationMapper = mockInformationMapper,
            placeMapper = mockPlaceMapper,
        )
    }

    @Test
    fun `GIVEN mock ChapterMapper, CharacterMapper, PlaceMapper and InformationMapper WHEN map Scenario THEN return ScenarioModel`() =
        runBlocking {
            // GIVEN
            whenever(mockChapterMapper.toDomain(ScenarioDbObjectMockFactory.chapterLocalDb))
                .thenReturn(ScenarioModelMockFactory.chapterModel)
            whenever(mockCharacterMapper.toDomain(ScenarioDbObjectMockFactory.characterLocalDb1))
                .thenReturn(ScenarioModelMockFactory.characterModel1)
            whenever(mockCharacterMapper.toDomain(ScenarioDbObjectMockFactory.characterLocalDb2))
                .thenReturn(ScenarioModelMockFactory.characterModel2)
            whenever(mockPlaceMapper.toDomain(ScenarioDbObjectMockFactory.placeLocalDb1))
                .thenReturn(ScenarioModelMockFactory.placeModel1)
            whenever(mockPlaceMapper.toDomain(ScenarioDbObjectMockFactory.placeLocalDb2))
                .thenReturn(ScenarioModelMockFactory.placeModel2)
            whenever(mockInformationMapper.toDomain(ScenarioDbObjectMockFactory.informationLocalDb))
                .thenReturn(ScenarioModelMockFactory.informationModel)

            // WHEN
            val scenarioModel = scenarioMapper.toDomain(ScenarioDbObjectMockFactory.scenarioLocalDb)

            // THEN
            Assertions.assertEquals(scenarioModel, ScenarioModelMockFactory.scenarioModelWithId)
        }

    @Test
    fun `GIVEN mock ChapterMapper, CharacterMapper, PlaceMapper and InformationMapper WHEN map ScenarioModel THEN return Scenario`() =
        runBlocking {
            // GIVEN
            whenever(mockChapterMapper.toDataLocalDb(ScenarioModelMockFactory.chapterModel))
                .thenReturn(ScenarioDbObjectMockFactory.chapterLocalDb)
            whenever(mockCharacterMapper.toDataLocalDb(ScenarioModelMockFactory.characterModel1))
                .thenReturn(ScenarioDbObjectMockFactory.characterLocalDb1)
            whenever(mockCharacterMapper.toDataLocalDb(ScenarioModelMockFactory.characterModel2))
                .thenReturn(ScenarioDbObjectMockFactory.characterLocalDb2)
            whenever(mockPlaceMapper.toDataLocalDb(ScenarioModelMockFactory.placeModel1))
                .thenReturn(ScenarioDbObjectMockFactory.placeLocalDb1)
            whenever(mockPlaceMapper.toDataLocalDb(ScenarioModelMockFactory.placeModel2))
                .thenReturn(ScenarioDbObjectMockFactory.placeLocalDb2)
            whenever(mockInformationMapper.toDataLocalDb(ScenarioModelMockFactory.informationModel))
                .thenReturn(ScenarioDbObjectMockFactory.informationLocalDb)

            // WHEN
            val scenarioModel = scenarioMapper.toDataLocalDb(ScenarioModelMockFactory.scenarioModelWithId)

            // THEN
            Assertions.assertEquals(scenarioModel, ScenarioDbObjectMockFactory.scenarioLocalDb)
        }

    @Test
    fun `GIVEN mock ChapterMapper, CharacterMapper, PlaceMapper and InformationMapper WHEN map ScenarioModel without Id THEN return Scenario`() =
        runBlocking {
            // GIVEN
            whenever(mockChapterMapper.toDataLocalDb(ScenarioModelMockFactory.chapterModel))
                .thenReturn(ScenarioDbObjectMockFactory.chapterLocalDb)
            whenever(mockCharacterMapper.toDataLocalDb(ScenarioModelMockFactory.characterModel1))
                .thenReturn(ScenarioDbObjectMockFactory.characterLocalDb1)
            whenever(mockCharacterMapper.toDataLocalDb(ScenarioModelMockFactory.characterModel2))
                .thenReturn(ScenarioDbObjectMockFactory.characterLocalDb2)
            whenever(mockPlaceMapper.toDataLocalDb(ScenarioModelMockFactory.placeModel1))
                .thenReturn(ScenarioDbObjectMockFactory.placeLocalDb1)
            whenever(mockPlaceMapper.toDataLocalDb(ScenarioModelMockFactory.placeModel2))
                .thenReturn(ScenarioDbObjectMockFactory.placeLocalDb2)
            whenever(mockInformationMapper.toDataLocalDb(ScenarioModelMockFactory.informationModel))
                .thenReturn(ScenarioDbObjectMockFactory.informationLocalDb)

            // WHEN
            val scenarioModel = scenarioMapper.toDataLocalDb(ScenarioModelMockFactory.scenarioModelWithoutId)

            // THEN
            Assertions.assertEquals(scenarioModel, ScenarioDbObjectMockFactory.scenarioLocalDb)
        }

    @Test
    fun `WHEN map empty Scenario THEN return empty ScenarioModel`() = runBlocking {
        // GIVEN
        whenever(mockInformationMapper.toDomain(InformationLocalDb())).thenReturn(InformationModel())

        // WHEN
        val scenarioModel = scenarioMapper.toDomain(ScenarioDbObjectMockFactory.emptyScenarioLocalDb)

        // THEN
        Assertions.assertEquals(
            scenarioModel,
            ScenarioModelMockFactory.emptyScenarioModelWithId,
        )
    }

    @Test
    fun `WHEN map empty ScenarioModel THEN return empty Scenario`() = runBlocking {
        // GIVEN
        whenever(mockInformationMapper.toDataLocalDb(InformationModel())).thenReturn(InformationLocalDb())

        // WHEN
        val scenarioModel = scenarioMapper.toDataLocalDb(ScenarioModelMockFactory.emptyScenarioModelWithId)

        // THEN
        Assertions.assertEquals(scenarioModel, ScenarioDbObjectMockFactory.emptyScenarioLocalDb)
    }
}
