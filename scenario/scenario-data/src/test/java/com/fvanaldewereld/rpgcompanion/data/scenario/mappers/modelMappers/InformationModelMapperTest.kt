package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers

import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDtoMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class InformationModelMapperTest {

    private lateinit var informationModelMapper: InformationModelMapper

    @BeforeEach
    fun setUp() {
        informationModelMapper = InformationModelMapperImpl()
    }

    @Test
    fun `WHEN map InformationEntity THEN return InformationModel`() {
        // GIVEN

        // WHEN
        val scenario = informationModelMapper.to(ScenarioDtoMockFactory.informationDto)

        // THEN
        assertEquals(scenario, ScenarioModelMockFactory.informationModel)
    }
}
