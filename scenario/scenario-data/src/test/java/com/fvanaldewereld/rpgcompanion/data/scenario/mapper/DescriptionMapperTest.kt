package com.fvanaldewereld.rpgcompanion.data.scenario.mapper

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.DescriptionModel
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDtoMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DescriptionMapperTest {

    private lateinit var descriptionMapper: DescriptionMapper

    @BeforeEach
    fun setUp() {
        descriptionMapper = DescriptionMapper()
    }

    @Test
    fun `WHEN map DescriptionDto THEN return DescriptionModel`() {
        // GIVEN

        // WHEN
        val scenario = descriptionMapper.toDomain(ScenarioDtoMockFactory.exampleDescriptionDto)

        // THEN
        assertEquals(
            actual = scenario,
            expected = ScenarioModelMockFactory.exampleDescriptionModel,
        )
    }

    @Test
    fun `WHEN map null THEN return DescriptionModel`() {
        // GIVEN

        // WHEN
        val scenario = descriptionMapper.toDomain(null)

        // THEN
        assertEquals(
            actual = scenario,
            expected = DescriptionModel(),
        )
    }
}
