package com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model

import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDtoMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AuthorModelMapperTest {

    private lateinit var authorModelMapper: AuthorModelMapper

    @BeforeEach
    fun setUp() {
        authorModelMapper = AuthorModelMapper()
    }

    @Test
    fun `WHEN map AuthorDto THEN return AuthorModel`() {
        // GIVEN

        // WHEN
        val scenario = authorModelMapper.toDomain(ScenarioDtoMockFactory.authorDto)

        // THEN
        assertEquals(scenario, ScenarioModelMockFactory.authorModel)
    }
}
