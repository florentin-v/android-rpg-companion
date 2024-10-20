package com.fvanaldewereld.rpgcompanion.data.scenario.mapper.localDb

import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDbObjectMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlaceMapperTest {

    private lateinit var placeMapper: PlaceMapper

    @BeforeEach
    fun setUp() {
        placeMapper = PlaceMapper()
    }

    @Test
    fun `WHEN map Place THEN return PlaceModel`() {
        // GIVEN

        // WHEN
        val scenario = placeMapper.toDomain(ScenarioDbObjectMockFactory.placeLocalDb1)

        // THEN
        Assertions.assertEquals(scenario, ScenarioModelMockFactory.placeModel1)
    }

    @Test
    fun `WHEN map PlaceModel THEN return Place`() {
        // GIVEN

        // WHEN
        val scenario = placeMapper.toDataLocalDb(ScenarioModelMockFactory.placeModel1)

        // THEN
        Assertions.assertEquals(scenario, ScenarioDbObjectMockFactory.placeLocalDb1)
    }
}
