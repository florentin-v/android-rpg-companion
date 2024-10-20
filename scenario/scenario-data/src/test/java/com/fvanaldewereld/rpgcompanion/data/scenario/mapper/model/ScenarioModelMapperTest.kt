package com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model

import BasicKoinTest
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ChapterListModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.CharacterListModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.MainInfoModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.PlaceListModel
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDtoMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.KoinApplication
import org.koin.dsl.module
import org.koin.test.inject
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class ScenarioModelMapperTest : BasicKoinTest() {

    private val mockAuthorModeMapper by inject<AuthorModelMapper>()
    private val mockChapterListModelMapper by inject<ChapterListModelMapper>()
    private val mockCharacterListModelMapper by inject<CharacterListModelMapper>()
    private val mockInformationModelMapper by inject<InformationModelMapper>()
    private val mockMainInfoModelMapper by inject<MainInfoModelMapper>()
    private val mockPlaceListModelMapper by inject<PlaceListModelMapper>()
    private val mockSummaryModelMapper by inject<SummaryModelMapper>()
    private val mockTitleModelMapper by inject<TitleModelMapper>()
    private lateinit var scenarioModelMapper: ScenarioModelMapper

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { mock<AuthorModelMapper>() }
                single { mock<ChapterListModelMapper>() }
                single { mock<CharacterListModelMapper>() }
                single { mock<InformationModelMapper>() }
                single { mock<MainInfoModelMapper>() }
                single { mock<PlaceListModelMapper>() }
                single { mock<SummaryModelMapper>() }
                single { mock<TitleModelMapper>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        scenarioModelMapper = ScenarioModelMapper(
            chapterListModelMapper = mockChapterListModelMapper,
            characterListModelMapper = mockCharacterListModelMapper,
            placeListModelMapper = mockPlaceListModelMapper,
            mainInfoModelMapper = mockMainInfoModelMapper
        )
    }

    @Test
    fun `WHEN map empty ScenarioDto THEN return empty ScenarioModel`() {
        // GIVEN
        whenever(mockChapterListModelMapper.toDomain(null)).thenReturn(ChapterListModel())
        whenever(mockCharacterListModelMapper.toDomain(null)).thenReturn(CharacterListModel())
        whenever(mockMainInfoModelMapper.toDomain(ScenarioDtoMockFactory.emptyScenarioDto)).thenReturn(MainInfoModel())
        whenever(mockPlaceListModelMapper.toDomain(null)).thenReturn(PlaceListModel())

        // WHEN
        val scenario = scenarioModelMapper.toDomain(ScenarioDtoMockFactory.emptyScenarioDto)

        // THEN
        assertEquals(scenario, ScenarioModelMockFactory.emptyScenarioModelWithoutId)
    }

    @Test
    fun `WHEN map ScenarioDto THEN return ScenarioModel`() {
        // GIVEN
        whenever(mockAuthorModeMapper.toDomain(ScenarioDtoMockFactory.authorDto))
            .thenReturn(ScenarioModelMockFactory.authorModel)
        whenever(mockChapterListModelMapper.toDomain(ScenarioDtoMockFactory.chapterListDto))
            .thenReturn(ScenarioModelMockFactory.chapterListModel)
        whenever(mockCharacterListModelMapper.toDomain(ScenarioDtoMockFactory.characterListDto))
            .thenReturn(ScenarioModelMockFactory.characterListModel)
        whenever(mockInformationModelMapper.toDomain(ScenarioDtoMockFactory.informationDto))
            .thenReturn(ScenarioModelMockFactory.informationModel)
        whenever(mockPlaceListModelMapper.toDomain(ScenarioDtoMockFactory.placeListDto))
            .thenReturn(ScenarioModelMockFactory.placeListModel)
        whenever(mockSummaryModelMapper.toDomain(ScenarioDtoMockFactory.summaryDto))
            .thenReturn(ScenarioModelMockFactory.summaryModel)
        whenever(mockTitleModelMapper.toDomain(ScenarioDtoMockFactory.titleDto))
            .thenReturn(ScenarioModelMockFactory.titleModel)
        whenever(mockMainInfoModelMapper.toDomain(ScenarioDtoMockFactory.scenarioDto))
            .thenReturn(ScenarioModelMockFactory.mainInfoModel)

        // WHEN
        val scenario = scenarioModelMapper.toDomain(ScenarioDtoMockFactory.scenarioDto)

        // THEN
        assertEquals(scenario, ScenarioModelMockFactory.scenarioModelWithoutId)
    }
}
