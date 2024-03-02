package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers

import BasicKoinTest
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDtoMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.KoinApplication
import org.koin.dsl.module
import org.koin.test.inject
import org.mockito.Mockito

class ScenarioModelMapperTest : BasicKoinTest() {

    private val mockAuthorModeMapper by inject<AuthorModelMapper>()
    private val mockChaptersModelMapper by inject<ChaptersModelMapper>()
    private val mockCharactersModelMapper by inject<CharactersModelMapper>()
    private val mockInformationModelMapper by inject<InformationModelMapper>()
    private val mockPlacesModelMapper by inject<PlacesModelMapper>()
    private val mockSummaryModelMapper by inject<SummaryModelMapper>()
    private val mockTitleModelMapper by inject<TitleModelMapper>()
    private lateinit var scenarioModelMapper: ScenarioModelMapper

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { Mockito.mock<AuthorModelMapper>() }
                single { Mockito.mock<ChaptersModelMapper>() }
                single { Mockito.mock<CharactersModelMapper>() }
                single { Mockito.mock<InformationModelMapper>() }
                single { Mockito.mock<PlacesModelMapper>() }
                single { Mockito.mock<SummaryModelMapper>() }
                single { Mockito.mock<TitleModelMapper>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        scenarioModelMapper = ScenarioModelMapperImpl()
    }

    @Test
    fun `WHEN map empty ScenarioEntity THEN return empty ScenarioModel`() {
        // GIVEN

        // WHEN
        val scenario = scenarioModelMapper.to(ScenarioDtoMockFactory.emptyScenarioDto)

        // THEN
        assertEquals(scenario, ScenarioModelMockFactory.emptyScenarioModelWithoutId)
    }

    @Test
    fun `WHEN map ScenarioEntity THEN return ScenarioModel`() {
        // GIVEN
        Mockito.`when`(mockAuthorModeMapper.to(ScenarioDtoMockFactory.authorDto))
            .thenReturn(ScenarioModelMockFactory.authorModel)
        Mockito.`when`(mockChaptersModelMapper.to(ScenarioDtoMockFactory.chaptersDto))
            .thenReturn(ScenarioModelMockFactory.chaptersModel)
        Mockito.`when`(mockCharactersModelMapper.to(ScenarioDtoMockFactory.charactersDto))
            .thenReturn(ScenarioModelMockFactory.charactersModel)
        Mockito.`when`(mockInformationModelMapper.to(ScenarioDtoMockFactory.informationDto))
            .thenReturn(ScenarioModelMockFactory.informationModel)
        Mockito.`when`(mockPlacesModelMapper.to(ScenarioDtoMockFactory.placesDto))
            .thenReturn(ScenarioModelMockFactory.placesModel)
        Mockito.`when`(mockSummaryModelMapper.to(ScenarioDtoMockFactory.summaryDto))
            .thenReturn(ScenarioModelMockFactory.summaryModel)
        Mockito.`when`(mockTitleModelMapper.to(ScenarioDtoMockFactory.titleDto))
            .thenReturn(ScenarioModelMockFactory.titleModel)

        // WHEN
        val scenario = scenarioModelMapper.to(ScenarioDtoMockFactory.scenarioDto)

        // THEN
        assertEquals(scenario, ScenarioModelMockFactory.scenarioModelWithoutId)
    }
}
