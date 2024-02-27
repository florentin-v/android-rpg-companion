package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.dbObjectMappers

import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDbObjectMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class InformationMapperTest {

    private lateinit var informationMapper: InformationMapper

    @BeforeEach
    fun setUp() {
        informationMapper = InformationMapperImpl()
    }

    @Test
    fun `WHEN map Information THEN return InformationModel`() {
        // GIVEN

        // WHEN
        val scenario = informationMapper.to(ScenarioDbObjectMockFactory.information)

        // THEN
        Assertions.assertEquals(scenario, ScenarioModelMockFactory.informationModel)
    }

    @Test
    fun `WHEN map InformationModel THEN return Information`() {
        // GIVEN

        // WHEN
        val scenario = informationMapper.from(ScenarioModelMockFactory.informationModel)

        // THEN
        Assertions.assertEquals(scenario, ScenarioDbObjectMockFactory.information)
    }
}
