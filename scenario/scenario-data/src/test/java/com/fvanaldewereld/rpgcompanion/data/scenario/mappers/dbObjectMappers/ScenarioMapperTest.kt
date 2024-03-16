package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.dbObjectMappers

import BasicKoinTest
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.InformationModel
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.embedded.Information
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
        scenarioMapper = ScenarioMapperImpl()
    }

    @Test
    fun `GIVEN mock ChapterMapper, CharacterMapper, PlaceMapper and InformationMapper WHEN map Scenario THEN return ScenarioModel`() =
        runBlocking {
            // GIVEN
            whenever(mockChapterMapper.to(ScenarioDbObjectMockFactory.chapter))
                .thenReturn(ScenarioModelMockFactory.chapterModel)
            whenever(mockCharacterMapper.to(ScenarioDbObjectMockFactory.character1))
                .thenReturn(ScenarioModelMockFactory.characterModel1)
            whenever(mockCharacterMapper.to(ScenarioDbObjectMockFactory.character2))
                .thenReturn(ScenarioModelMockFactory.characterModel2)
            whenever(mockPlaceMapper.to(ScenarioDbObjectMockFactory.place1))
                .thenReturn(ScenarioModelMockFactory.placeModel1)
            whenever(mockPlaceMapper.to(ScenarioDbObjectMockFactory.place2))
                .thenReturn(ScenarioModelMockFactory.placeModel2)
            whenever(mockInformationMapper.to(ScenarioDbObjectMockFactory.information))
                .thenReturn(ScenarioModelMockFactory.informationModel)

            // WHEN
            val scenarioModel = scenarioMapper.to(ScenarioDbObjectMockFactory.scenario)

            // THEN
            Assertions.assertEquals(scenarioModel, ScenarioModelMockFactory.scenarioModelWithId)
        }

    @Test
    fun `GIVEN mock ChapterMapper, CharacterMapper, PlaceMapper and InformationMapper WHEN map ScenarioModel THEN return Scenario`() =
        runBlocking {
            // GIVEN
            whenever(mockChapterMapper.from(ScenarioModelMockFactory.chapterModel))
                .thenReturn(ScenarioDbObjectMockFactory.chapter)
            whenever(mockCharacterMapper.from(ScenarioModelMockFactory.characterModel1))
                .thenReturn(ScenarioDbObjectMockFactory.character1)
            whenever(mockCharacterMapper.from(ScenarioModelMockFactory.characterModel2))
                .thenReturn(ScenarioDbObjectMockFactory.character2)
            whenever(mockPlaceMapper.from(ScenarioModelMockFactory.placeModel1))
                .thenReturn(ScenarioDbObjectMockFactory.place1)
            whenever(mockPlaceMapper.from(ScenarioModelMockFactory.placeModel2))
                .thenReturn(ScenarioDbObjectMockFactory.place2)
            whenever(mockInformationMapper.from(ScenarioModelMockFactory.informationModel))
                .thenReturn(ScenarioDbObjectMockFactory.information)

            // WHEN
            val scenarioModel = scenarioMapper.from(ScenarioModelMockFactory.scenarioModelWithId)

            // THEN
            Assertions.assertEquals(scenarioModel, ScenarioDbObjectMockFactory.scenario)
        }

    @Test
    fun `GIVEN mock ChapterMapper, CharacterMapper, PlaceMapper and InformationMapper WHEN map ScenarioModel without Id THEN return Scenario`() =
        runBlocking {
            // GIVEN
            whenever(mockChapterMapper.from(ScenarioModelMockFactory.chapterModel))
                .thenReturn(ScenarioDbObjectMockFactory.chapter)
            whenever(mockCharacterMapper.from(ScenarioModelMockFactory.characterModel1))
                .thenReturn(ScenarioDbObjectMockFactory.character1)
            whenever(mockCharacterMapper.from(ScenarioModelMockFactory.characterModel2))
                .thenReturn(ScenarioDbObjectMockFactory.character2)
            whenever(mockPlaceMapper.from(ScenarioModelMockFactory.placeModel1))
                .thenReturn(ScenarioDbObjectMockFactory.place1)
            whenever(mockPlaceMapper.from(ScenarioModelMockFactory.placeModel2))
                .thenReturn(ScenarioDbObjectMockFactory.place2)
            whenever(mockInformationMapper.from(ScenarioModelMockFactory.informationModel))
                .thenReturn(ScenarioDbObjectMockFactory.information)

            // WHEN
            val scenarioModel = scenarioMapper.from(ScenarioModelMockFactory.scenarioModelWithoutId)

            // THEN
            Assertions.assertEquals(scenarioModel, ScenarioDbObjectMockFactory.scenario)
        }

    @Test
    fun `WHEN map empty Scenario THEN return empty ScenarioModel`() = runBlocking {
        // GIVEN
        whenever(mockInformationMapper.to(Information())).thenReturn(InformationModel())

        // WHEN
        val scenarioModel = scenarioMapper.to(ScenarioDbObjectMockFactory.emptyScenario)

        // THEN
        Assertions.assertEquals(
            scenarioModel,
            ScenarioModelMockFactory.emptyScenarioModelWithId,
        )
    }

    @Test
    fun `WHEN map empty ScenarioModel THEN return empty Scenario`() = runBlocking {
        // GIVEN
        whenever(mockInformationMapper.from(InformationModel())).thenReturn(Information())

        // WHEN
        val scenarioModel = scenarioMapper.from(ScenarioModelMockFactory.emptyScenarioModelWithId)

        // THEN
        Assertions.assertEquals(scenarioModel, ScenarioDbObjectMockFactory.emptyScenario)
    }
}
