package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers

import BasicKoinTest
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ChaptersModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.CharactersModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.MainInfoModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.PlacesModel
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
    private val mockChaptersModelMapper by inject<ChaptersModelMapper>()
    private val mockCharactersModelMapper by inject<CharactersModelMapper>()
    private val mockInformationModelMapper by inject<InformationModelMapper>()
    private val mockMainInfoModelMapper by inject<MainInfoModelMapper>()
    private val mockPlacesModelMapper by inject<PlacesModelMapper>()
    private val mockSummaryModelMapper by inject<SummaryModelMapper>()
    private val mockTitleModelMapper by inject<TitleModelMapper>()
    private lateinit var scenarioModelMapper: ScenarioModelMapper

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { mock<AuthorModelMapper>() }
                single { mock<ChaptersModelMapper>() }
                single { mock<CharactersModelMapper>() }
                single { mock<InformationModelMapper>() }
                single { mock<MainInfoModelMapper>() }
                single { mock<PlacesModelMapper>() }
                single { mock<SummaryModelMapper>() }
                single { mock<TitleModelMapper>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        scenarioModelMapper = ScenarioModelMapperImpl()
    }

    @Test
    fun `WHEN map empty ScenarioDto THEN return empty ScenarioModel`() {
        // GIVEN
        whenever(mockChaptersModelMapper.to(null)).thenReturn(ChaptersModel())
        whenever(mockCharactersModelMapper.to(null)).thenReturn(CharactersModel())
        whenever(mockMainInfoModelMapper.to(ScenarioDtoMockFactory.emptyScenarioDto)).thenReturn(MainInfoModel())
        whenever(mockPlacesModelMapper.to(null)).thenReturn(PlacesModel())

        // WHEN
        val scenario = scenarioModelMapper.to(ScenarioDtoMockFactory.emptyScenarioDto)

        // THEN
        assertEquals(scenario, ScenarioModelMockFactory.emptyScenarioModelWithoutId)
    }

    @Test
    fun `WHEN map ScenarioDto THEN return ScenarioModel`() {
        // GIVEN
        whenever(mockAuthorModeMapper.to(ScenarioDtoMockFactory.authorDto))
            .thenReturn(ScenarioModelMockFactory.authorModel)
        whenever(mockChaptersModelMapper.to(ScenarioDtoMockFactory.chaptersDto))
            .thenReturn(ScenarioModelMockFactory.chaptersModel)
        whenever(mockCharactersModelMapper.to(ScenarioDtoMockFactory.charactersDto))
            .thenReturn(ScenarioModelMockFactory.charactersModel)
        whenever(mockInformationModelMapper.to(ScenarioDtoMockFactory.informationDto))
            .thenReturn(ScenarioModelMockFactory.informationModel)
        whenever(mockPlacesModelMapper.to(ScenarioDtoMockFactory.placesDto))
            .thenReturn(ScenarioModelMockFactory.placesModel)
        whenever(mockSummaryModelMapper.to(ScenarioDtoMockFactory.summaryDto))
            .thenReturn(ScenarioModelMockFactory.summaryModel)
        whenever(mockTitleModelMapper.to(ScenarioDtoMockFactory.titleDto))
            .thenReturn(ScenarioModelMockFactory.titleModel)
        whenever(mockMainInfoModelMapper.to(ScenarioDtoMockFactory.scenarioDto))
            .thenReturn(ScenarioModelMockFactory.mainInfoModel)

        // WHEN
        val scenario = scenarioModelMapper.to(ScenarioDtoMockFactory.scenarioDto)

        // THEN
        assertEquals(scenario, ScenarioModelMockFactory.scenarioModelWithoutId)
    }
}
