package com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.TitleModel
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDtoMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TitleModelMapperTest {

    private lateinit var titleModelMapper: TitleModelMapper

    @BeforeEach
    fun setUp() {
        titleModelMapper = TitleModelMapper()
    }

    @Test
    fun `WHEN map TitleDto THEN return TitleModel`() {
        // GIVEN

        // WHEN
        val scenario = titleModelMapper.toDomain(ScenarioDtoMockFactory.titleDto)

        // THEN
        assertEquals(scenario, ScenarioModelMockFactory.titleModel)
    }

    @Test
    fun `WHEN map null THEN return TitleModel with null value`() {
        // GIVEN

        // WHEN
        val scenario = titleModelMapper.toDomain(null)

        // THEN
        assertEquals(scenario, TitleModel())
    }
}
