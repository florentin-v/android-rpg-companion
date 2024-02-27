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

class SummaryModelMapperTest : BasicKoinTest() {

    private val mockDescriptionModeMapper by inject<DescriptionModelMapper>()
    private lateinit var summaryModelMapper: SummaryModelMapper

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { Mockito.mock<DescriptionModelMapper>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        summaryModelMapper = SummaryModelMapperImpl()
    }

    @Test
    fun `GIVEN mock DescriptionModelMapper WHEN map SummaryEntity THEN return SummaryModel`() {
        // GIVEN
        Mockito.`when`(mockDescriptionModeMapper.to(ScenarioDtoMockFactory.summaryDescriptionDto))
            .thenReturn(ScenarioModelMockFactory.summaryDescriptionModel)

        // WHEN
        val scenario = summaryModelMapper.to(ScenarioDtoMockFactory.summaryDto)

        // THEN
        assertEquals(scenario, ScenarioModelMockFactory.summaryModel)
    }
}
