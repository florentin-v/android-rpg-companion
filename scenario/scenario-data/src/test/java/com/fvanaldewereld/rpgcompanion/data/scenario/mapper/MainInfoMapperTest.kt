package com.fvanaldewereld.rpgcompanion.data.scenario.mapper

import com.fvanaldewereld.rpgcompanion.test.common.BasicKoinTest
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.AuthorModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.InformationModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.MainInfoModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.SummaryModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.TitleModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.InformationDto
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDtoMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.KoinApplication
import org.koin.dsl.module
import org.koin.test.inject
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class MainInfoMapperTest : BasicKoinTest() {

    private val mockAuthorMapper by inject<AuthorMapper>()
    private val mockInformationMapper by inject<InformationMapper>()
    private val mockSummaryMapper by inject<SummaryMapper>()
    private val mockTitleMapper by inject<TitleMapper>()

    private lateinit var mainInfoMapper: MainInfoMapper

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { mock<AuthorMapper>() }
                single { mock<InformationMapper>() }
                single { mock<SummaryMapper>() }
                single { mock<TitleMapper>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        mainInfoMapper = MainInfoMapper(
            authorModeMapper = mockAuthorMapper,
            informationMapper = mockInformationMapper,
            summaryMapper = mockSummaryMapper,
            titleMapper = mockTitleMapper,
        )

        assertNotNull(mockAuthorMapper)
        assertNotNull(mockInformationMapper)
        assertNotNull(mockSummaryMapper)
        assertNotNull(mockTitleMapper)
    }

    @Test
    fun `WHEN map empty ScenarioDto THEN return empty MainInfoModel`() {
        // GIVEN
        whenever(mockAuthorMapper.toDomain(null))
            .thenReturn(AuthorModel())
        whenever(mockInformationMapper.toDomain(InformationDto()))
            .thenReturn(InformationModel())
        whenever(mockSummaryMapper.toDomain(null))
            .thenReturn(SummaryModel())
        whenever(mockTitleMapper.toDomain(null))
            .thenReturn(TitleModel())

        // WHEN
        val scenario = mainInfoMapper.toDomain(ScenarioDtoMockFactory.emptyScenarioDto)

        // THEN
        assertEquals(
            actual = scenario,
            expected = MainInfoModel(),
        )
    }

    @Test
    fun `WHEN map ScenarioDto THEN return MainInfoModel`() {
        // GIVEN
        whenever(mockAuthorMapper.toDomain(ScenarioDtoMockFactory.authorDto))
            .thenReturn(ScenarioModelMockFactory.authorModel)
        whenever(mockInformationMapper.toDomain(ScenarioDtoMockFactory.informationDto))
            .thenReturn(ScenarioModelMockFactory.informationModel)
        whenever(mockSummaryMapper.toDomain(ScenarioDtoMockFactory.summaryDto))
            .thenReturn(ScenarioModelMockFactory.summaryModel)
        whenever(mockTitleMapper.toDomain(ScenarioDtoMockFactory.titleDto))
            .thenReturn(ScenarioModelMockFactory.titleModel)

        // WHEN
        val scenario = mainInfoMapper.toDomain(ScenarioDtoMockFactory.scenarioDto)

        // THEN
        assertEquals(
            actual = scenario,
            expected = ScenarioModelMockFactory.mainInfoModel,
        )
    }
}
