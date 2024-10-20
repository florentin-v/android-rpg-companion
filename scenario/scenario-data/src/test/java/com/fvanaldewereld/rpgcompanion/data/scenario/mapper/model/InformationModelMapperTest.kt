package com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model

import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDtoMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class InformationModelMapperTest {

    private lateinit var informationModelMapper: InformationModelMapper

    @BeforeEach
    fun setUp() {
        informationModelMapper = InformationModelMapper()
    }

    @Test
    fun `WHEN map InformationDto THEN return InformationModel`() {
        // GIVEN

        // WHEN
        val scenario = informationModelMapper.toDomain(ScenarioDtoMockFactory.informationDto)

        // THEN
        assertEquals(scenario, ScenarioModelMockFactory.informationModel)
    }
}
