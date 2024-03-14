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
import org.mockito.Mockito

class ScenarioMapperTest : BasicKoinTest() {

    private val mockChapterMapper by inject<ChapterMapper>()
    private val mockCharacterMapper by inject<CharacterMapper>()
    private val mockPlaceMapper by inject<PlaceMapper>()
    private val mockInformationMapper by inject<InformationMapper>()

    private lateinit var scenarioMapper: ScenarioMapper

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { Mockito.mock<ChapterMapper>() }
                single { Mockito.mock<CharacterMapper>() }
                single { Mockito.mock<PlaceMapper>() }
                single { Mockito.mock<InformationMapper>() }
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
            Mockito.`when`(mockChapterMapper.to(ScenarioDbObjectMockFactory.chapter))
                .thenReturn(ScenarioModelMockFactory.chapterModel)
            Mockito.`when`(mockCharacterMapper.to(ScenarioDbObjectMockFactory.character1))
                .thenReturn(ScenarioModelMockFactory.characterModel1)
            Mockito.`when`(mockCharacterMapper.to(ScenarioDbObjectMockFactory.character2))
                .thenReturn(ScenarioModelMockFactory.characterModel2)
            Mockito.`when`(mockPlaceMapper.to(ScenarioDbObjectMockFactory.place1))
                .thenReturn(ScenarioModelMockFactory.placeModel1)
            Mockito.`when`(mockPlaceMapper.to(ScenarioDbObjectMockFactory.place2))
                .thenReturn(ScenarioModelMockFactory.placeModel2)
            Mockito.`when`(mockInformationMapper.to(ScenarioDbObjectMockFactory.information))
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
            Mockito.`when`(mockChapterMapper.from(ScenarioModelMockFactory.chapterModel))
                .thenReturn(ScenarioDbObjectMockFactory.chapter)
            Mockito.`when`(mockCharacterMapper.from(ScenarioModelMockFactory.characterModel1))
                .thenReturn(ScenarioDbObjectMockFactory.character1)
            Mockito.`when`(mockCharacterMapper.from(ScenarioModelMockFactory.characterModel2))
                .thenReturn(ScenarioDbObjectMockFactory.character2)
            Mockito.`when`(mockPlaceMapper.from(ScenarioModelMockFactory.placeModel1))
                .thenReturn(ScenarioDbObjectMockFactory.place1)
            Mockito.`when`(mockPlaceMapper.from(ScenarioModelMockFactory.placeModel2))
                .thenReturn(ScenarioDbObjectMockFactory.place2)
            Mockito.`when`(mockInformationMapper.from(ScenarioModelMockFactory.informationModel))
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
            Mockito.`when`(mockChapterMapper.from(ScenarioModelMockFactory.chapterModel))
                .thenReturn(ScenarioDbObjectMockFactory.chapter)
            Mockito.`when`(mockCharacterMapper.from(ScenarioModelMockFactory.characterModel1))
                .thenReturn(ScenarioDbObjectMockFactory.character1)
            Mockito.`when`(mockCharacterMapper.from(ScenarioModelMockFactory.characterModel2))
                .thenReturn(ScenarioDbObjectMockFactory.character2)
            Mockito.`when`(mockPlaceMapper.from(ScenarioModelMockFactory.placeModel1))
                .thenReturn(ScenarioDbObjectMockFactory.place1)
            Mockito.`when`(mockPlaceMapper.from(ScenarioModelMockFactory.placeModel2))
                .thenReturn(ScenarioDbObjectMockFactory.place2)
            Mockito.`when`(mockInformationMapper.from(ScenarioModelMockFactory.informationModel))
                .thenReturn(ScenarioDbObjectMockFactory.information)

            // WHEN
            val scenarioModel = scenarioMapper.from(ScenarioModelMockFactory.scenarioModelWithoutId)

            // THEN
            Assertions.assertEquals(scenarioModel, ScenarioDbObjectMockFactory.scenario)
        }

    @Test
    fun `WHEN map empty Scenario THEN return empty ScenarioModel`() = runBlocking {
        // GIVEN
        Mockito.`when`(mockInformationMapper.to(Information())).thenReturn(InformationModel())

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
        Mockito.`when`(mockInformationMapper.from(InformationModel())).thenReturn(Information())

        // WHEN
        val scenarioModel = scenarioMapper.from(ScenarioModelMockFactory.emptyScenarioModelWithId)

        // THEN
        Assertions.assertEquals(scenarioModel, ScenarioDbObjectMockFactory.emptyScenario)
    }
}
