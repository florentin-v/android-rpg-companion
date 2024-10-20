package com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model

import BasicKoinTest
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

class SummaryModelMapperTest : BasicKoinTest() {

    private val mockDescriptionModeMapper by inject<DescriptionModelMapper>()
    private lateinit var summaryModelMapper: SummaryModelMapper

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { mock<DescriptionModelMapper>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        summaryModelMapper = SummaryModelMapper(
            descriptionModelMapper = mockDescriptionModeMapper
        )
    }

    @Test
    fun `GIVEN mock DescriptionModelMapper WHEN map SummaryDto THEN return SummaryModel`() {
        // GIVEN
        whenever(mockDescriptionModeMapper.toDomain(ScenarioDtoMockFactory.summaryDescriptionDto))
            .thenReturn(ScenarioModelMockFactory.summaryDescriptionModel)

        // WHEN
        val scenario = summaryModelMapper.toDomain(ScenarioDtoMockFactory.summaryDto)

        // THEN
        assertEquals(scenario, ScenarioModelMockFactory.summaryModel)
    }
}
