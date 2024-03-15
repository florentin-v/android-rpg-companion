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
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class MainInfoModelMapperTest : BasicKoinTest() {

    private val mockAuthorModeMapper by inject<AuthorModelMapper>()
    private val mockInformationModelMapper by inject<InformationModelMapper>()
    private val mockSummaryModelMapper by inject<SummaryModelMapper>()
    private val mockTitleModelMapper by inject<TitleModelMapper>()
    private lateinit var mainInfoModelMapper: MainInfoModelMapper

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
        mainInfoModelMapper = MainInfoModelMapperImpl()
    }

    @Test
    fun `WHEN map empty ScenarioDto THEN return empty MainInfoModel`() {
        // GIVEN
        whenever(mockAuthorModeMapper.to(null)).thenReturn(AuthorModel())
        whenever(mockInformationModelMapper.to(InformationDto())).thenReturn(InformationModel())
        whenever(mockSummaryModelMapper.to(null)).thenReturn(SummaryModel())
        whenever(mockTitleModelMapper.to(null)).thenReturn(TitleModel())

        // WHEN
        val scenario = mainInfoModelMapper.to(ScenarioDtoMockFactory.emptyScenarioDto)

        // THEN
        assertEquals(scenario, MainInfoModel())
    }

    @Test
    fun `WHEN map ScenarioDto THEN return MainInfoModel`() {
        // GIVEN
        whenever(mockAuthorModeMapper.to(ScenarioDtoMockFactory.authorDto))
            .thenReturn(ScenarioModelMockFactory.authorModel)
        whenever(mockInformationModelMapper.to(ScenarioDtoMockFactory.informationDto))
            .thenReturn(ScenarioModelMockFactory.informationModel)
        whenever(mockSummaryModelMapper.to(ScenarioDtoMockFactory.summaryDto))
            .thenReturn(ScenarioModelMockFactory.summaryModel)
        whenever(mockTitleModelMapper.to(ScenarioDtoMockFactory.titleDto))
            .thenReturn(ScenarioModelMockFactory.titleModel)

        // WHEN
        val scenario = mainInfoModelMapper.to(ScenarioDtoMockFactory.scenarioDto)

        // THEN
        assertEquals(scenario, ScenarioModelMockFactory.mainInfoModel)
    }
}
