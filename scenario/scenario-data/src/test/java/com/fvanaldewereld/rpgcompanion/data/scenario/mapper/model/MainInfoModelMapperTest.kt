package com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model

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
        mainInfoModelMapper = MainInfoModelMapper(
            authorModeMapper = mockAuthorModeMapper,
            informationModelMapper = mockInformationModelMapper,
            summaryModelMapper = mockSummaryModelMapper,
            titleModelMapper = mockTitleModelMapper,
        )
    }

    @Test
    fun `WHEN map empty ScenarioDto THEN return empty MainInfoModel`() {
        // GIVEN
        whenever(mockAuthorModeMapper.toDomain(null)).thenReturn(AuthorModel())
        whenever(mockInformationModelMapper.toDomain(InformationDto())).thenReturn(InformationModel())
        whenever(mockSummaryModelMapper.toDomain(null)).thenReturn(SummaryModel())
        whenever(mockTitleModelMapper.toDomain(null)).thenReturn(TitleModel())

        // WHEN
        val scenario = mainInfoModelMapper.toDomain(ScenarioDtoMockFactory.emptyScenarioDto)

        // THEN
        assertEquals(scenario, MainInfoModel())
    }

    @Test
    fun `WHEN map ScenarioDto THEN return MainInfoModel`() {
        // GIVEN
        whenever(mockAuthorModeMapper.toDomain(ScenarioDtoMockFactory.authorDto))
            .thenReturn(ScenarioModelMockFactory.authorModel)
        whenever(mockInformationModelMapper.toDomain(ScenarioDtoMockFactory.informationDto))
            .thenReturn(ScenarioModelMockFactory.informationModel)
        whenever(mockSummaryModelMapper.toDomain(ScenarioDtoMockFactory.summaryDto))
            .thenReturn(ScenarioModelMockFactory.summaryModel)
        whenever(mockTitleModelMapper.toDomain(ScenarioDtoMockFactory.titleDto))
            .thenReturn(ScenarioModelMockFactory.titleModel)

        // WHEN
        val scenario = mainInfoModelMapper.toDomain(ScenarioDtoMockFactory.scenarioDto)

        // THEN
        assertEquals(scenario, ScenarioModelMockFactory.mainInfoModel)
    }
}
