package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers

import BasicKoinTest
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.AuthorModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.InformationModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.MainInfoModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.SummaryModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.TitleModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.InformationDto
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDtoMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.KoinApplication
import org.koin.dsl.module
import org.koin.test.inject
import org.mockito.Mockito

class MainInfoModelMapperTest : BasicKoinTest() {

    private val mockAuthorModeMapper by inject<AuthorModelMapper>()
    private val mockInformationModelMapper by inject<InformationModelMapper>()
    private val mockSummaryModelMapper by inject<SummaryModelMapper>()
    private val mockTitleModelMapper by inject<TitleModelMapper>()
    private lateinit var mainInfoModelMapper: MainInfoModelMapper

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { Mockito.mock<AuthorModelMapper>() }
                single { Mockito.mock<ChaptersModelMapper>() }
                single { Mockito.mock<CharactersModelMapper>() }
                single { Mockito.mock<InformationModelMapper>() }
                single { Mockito.mock<MainInfoModelMapper>() }
                single { Mockito.mock<PlacesModelMapper>() }
                single { Mockito.mock<SummaryModelMapper>() }
                single { Mockito.mock<TitleModelMapper>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        mainInfoModelMapper = MainInfoModelMapperImpl()
    }

    @Test
    fun `WHEN map empty ScenarioDto THEN return empty MainInfoModel`() {
        // GIVEN
        Mockito.`when`(mockAuthorModeMapper.to(null)).thenReturn(AuthorModel())
        Mockito.`when`(mockInformationModelMapper.to(InformationDto())).thenReturn(InformationModel())
        Mockito.`when`(mockSummaryModelMapper.to(null)).thenReturn(SummaryModel())
        Mockito.`when`(mockTitleModelMapper.to(null)).thenReturn(TitleModel())

        // WHEN
        val scenario = mainInfoModelMapper.to(ScenarioDtoMockFactory.emptyScenarioDto)

        // THEN
        assertEquals(scenario, MainInfoModel())
    }

    @Test
    fun `WHEN map ScenarioDto THEN return MainInfoModel`() {
        // GIVEN
        Mockito.`when`(mockAuthorModeMapper.to(ScenarioDtoMockFactory.authorDto))
            .thenReturn(ScenarioModelMockFactory.authorModel)
        Mockito.`when`(mockInformationModelMapper.to(ScenarioDtoMockFactory.informationDto))
            .thenReturn(ScenarioModelMockFactory.informationModel)
        Mockito.`when`(mockSummaryModelMapper.to(ScenarioDtoMockFactory.summaryDto))
            .thenReturn(ScenarioModelMockFactory.summaryModel)
        Mockito.`when`(mockTitleModelMapper.to(ScenarioDtoMockFactory.titleDto))
            .thenReturn(ScenarioModelMockFactory.titleModel)

        // WHEN
        val scenario = mainInfoModelMapper.to(ScenarioDtoMockFactory.scenarioDto)

        // THEN
        assertEquals(scenario, ScenarioModelMockFactory.mainInfoModel)
    }
}
