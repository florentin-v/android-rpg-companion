package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.dtoMappers

import com.fvanaldewereld.rpgcompanion.mockFactory.GoogleDocsMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDtoMockFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import java.net.URL

class ScenarioDtoMapperTest {

    private lateinit var scenarioDtoMapper: ScenarioDtoMapper

    private val mockedURL = mock(URL::class.java)

    @BeforeEach
    fun setUp() {
        scenarioDtoMapper = ScenarioDtoMapperImpl()
    }

    @Test
    fun `GIVEN empty Document WHEN map THEN return empty ScenarioDto`() {
        // GIVEN
        whenever(mockedURL.path).thenReturn(GoogleDocsMockFactory.googleDocsUrl.path)

        // WHEN
        val scenario = scenarioDtoMapper.to(GoogleDocsMockFactory.emptyGoogleDocsDocument)

        // THEN
        assertEquals(scenario, ScenarioDtoMockFactory.emptyScenarioDto)
    }

    @Test
    fun `GIVEN Document WHEN map THEN return ScenarioDto`() {
        // GIVEN
        whenever(mockedURL.path).thenReturn(GoogleDocsMockFactory.googleDocsUrl.path)

        // WHEN
        val scenario = scenarioDtoMapper.to(GoogleDocsMockFactory.googleDocsDocument)

        // THEN
        assertEquals(scenario, ScenarioDtoMockFactory.scenarioDto)
    }
}
