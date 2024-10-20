package com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.DescriptionModel
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDtoMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DescriptionModelMapperTest {

    private lateinit var descriptionModelMapper: DescriptionModelMapper

    @BeforeEach
    fun setUp() {
        descriptionModelMapper = DescriptionModelMapper()
    }

    @Test
    fun `WHEN map DescriptionDto THEN return DescriptionModel`() {
        // GIVEN

        // WHEN
        val scenario = descriptionModelMapper.toDomain(ScenarioDtoMockFactory.exampleDescriptionDto)

        // THEN
        assertEquals(scenario, ScenarioModelMockFactory.exampleDescriptionModel)
    }

    @Test
    fun `WHEN map null THEN return DescriptionModel`() {
        // GIVEN

        // WHEN
        val scenario = descriptionModelMapper.toDomain(null)

        // THEN
        assertEquals(scenario, DescriptionModel())
    }
}
