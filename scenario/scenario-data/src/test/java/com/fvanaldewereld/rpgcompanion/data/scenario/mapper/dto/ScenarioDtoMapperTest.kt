package com.fvanaldewereld.rpgcompanion.data.scenario.mapper.dto

import com.fvanaldewereld.rpgcompanion.mockFactory.GoogleDocsMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDtoMockFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ScenarioDtoMapperTest {

    private lateinit var scenarioDtoMapper: ScenarioDtoMapper

    @BeforeEach
    fun setUp() {
        scenarioDtoMapper = ScenarioDtoMapper()
    }

    @Test
    fun `WHEN map empty Document THEN return empty ScenarioDto`() {
        // WHEN
        val scenario = scenarioDtoMapper.toDataDto(GoogleDocsMockFactory.emptyGoogleDocsDocument)

        // THEN
        assertEquals(scenario, ScenarioDtoMockFactory.emptyScenarioDto)
    }

    @Test
    fun `WHEN map Document THEN return ScenarioDto`() {
        // WHEN
        val scenario = scenarioDtoMapper.toDataDto(GoogleDocsMockFactory.googleDocsDocument)

        // THEN
        assertEquals(scenario, ScenarioDtoMockFactory.scenarioDto)
    }
}
