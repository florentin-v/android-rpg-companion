package com.fvanaldewereld.rpgcompanion.data.scenario.mapper

import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDtoMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory
import com.fvanaldewereld.rpgcompanion.test.common.BasicKoinTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.KoinApplication
import org.koin.dsl.module
import org.koin.test.inject
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class SummaryMapperTest : BasicKoinTest() {

    private val mockDescriptionMapper by inject<DescriptionMapper>()
    private lateinit var summaryMapper: SummaryMapper

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { mock<DescriptionMapper>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        summaryMapper = SummaryMapper(
            descriptionMapper = mockDescriptionMapper,
        )
    }

    @Test
    fun `WHEN map SummaryDto THEN return SummaryModel`() {
        // GIVEN
        whenever(mockDescriptionMapper.toDomain(ScenarioDtoMockFactory.summaryDescriptionDto))
            .thenReturn(ScenarioModelMockFactory.summaryDescriptionModel)

        // WHEN
        val scenario = summaryMapper.toDomain(ScenarioDtoMockFactory.summaryDto)

        // THEN
        assertEquals(
            actual = scenario,
            expected = ScenarioModelMockFactory.summaryModel,
        )
    }
}
