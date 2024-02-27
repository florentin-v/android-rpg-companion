package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.dtoMappers

import com.fvanaldewereld.rpgcompanion.data.scenario.sources.googleDocs.extensions.extractGoogleDocumentId
import com.fvanaldewereld.rpgcompanion.mockFactory.GoogleDocsMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDtoMockFactory
import io.mockk.every
import io.mockk.mockkStatic
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.net.URL

class ScenarioDtoMapperTest {

    private lateinit var scenarioDtoMapper: ScenarioDtoMapper

    @BeforeEach
    fun setUp() {
        scenarioDtoMapper = ScenarioDtoMapperImpl()
    }

    @Test
    fun `GIVEN empty Document WHEN map THEN return empty ScenarioEntity`() {
        // GIVEN
        mockkStatic("com.fvanaldewereld.rpgcompanion.data.scenario.sources.googleDocs.extensions.UrlExtensionKt")
        every { any<URL>().extractGoogleDocumentId() } returns GoogleDocsMockFactory.GOOGLE_DOCS_DOCUMENT_ID

        // WHEN
        val scenario = scenarioDtoMapper.to(GoogleDocsMockFactory.emptyGoogleDocsDocument)

        // THEN
        assertEquals(scenario, ScenarioDtoMockFactory.emptyScenarioDto)
    }

    @Test
    fun `GIVEN Document WHEN map THEN return ScenarioEntity`() {
        // GIVEN
        mockkStatic("com.fvanaldewereld.rpgcompanion.data.scenario.sources.googleDocs.extensions.UrlExtensionKt")
        every { any<URL>().extractGoogleDocumentId() } returns GoogleDocsMockFactory.GOOGLE_DOCS_DOCUMENT_ID

        // WHEN
        val scenario = scenarioDtoMapper.to(GoogleDocsMockFactory.googleDocsDocument)

        // THEN
        assertEquals(scenario, ScenarioDtoMockFactory.scenarioDto)
    }
}
