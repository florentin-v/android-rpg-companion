package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers

import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDtoMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TitleModelMapperTest {

    private lateinit var titleModelMapper: TitleModelMapper

    @BeforeEach
    fun setUp() {
        titleModelMapper = TitleModelMapperImpl()
    }

    @Test
    fun `WHEN map TitleEntity THEN return TitleModel`() {
        // GIVEN

        // WHEN
        val scenario = titleModelMapper.to(ScenarioDtoMockFactory.titleDto)

        // THEN
        assertEquals(scenario, ScenarioModelMockFactory.titleModel)
    }
}
