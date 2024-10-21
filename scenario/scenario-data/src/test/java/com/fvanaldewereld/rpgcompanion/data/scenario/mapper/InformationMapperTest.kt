package com.fvanaldewereld.rpgcompanion.data.scenario.mapper

import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDbObjectMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDtoMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class InformationMapperTest {

    private lateinit var informationMapper: InformationMapper

    @BeforeEach
    fun setUp() {
        informationMapper = InformationMapper()
    }

    @Test
    fun `WHEN map InformationDto THEN return InformationModel`() {
        // GIVEN

        // WHEN
        val scenario = informationMapper.toDomain(ScenarioDtoMockFactory.informationDto)

        // THEN
        assertEquals(
            actual = scenario,
            expected = ScenarioModelMockFactory.informationModel,
        )
    }

    @Test
    fun `WHEN map InformationLocalDb THEN return InformationModel`() {
        // GIVEN

        // WHEN
        val scenario = informationMapper.toDomain(ScenarioDbObjectMockFactory.informationLocalDb)

        // THEN
        assertEquals(
            actual = scenario,
            expected = ScenarioModelMockFactory.informationModel,
        )
    }

    @Test
    fun `WHEN map InformationModel THEN return InformationLocalDb`() {
        // GIVEN

        // WHEN
        val scenario = informationMapper.toDataLocalDb(ScenarioModelMockFactory.informationModel)

        // THEN
        assertEquals(
            actual = scenario,
            expected = ScenarioDbObjectMockFactory.informationLocalDb,
        )
    }
}
