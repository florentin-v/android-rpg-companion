package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers

import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDtoMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AuthorModelMapperTest {

    private lateinit var authorModelMapper: AuthorModelMapper

    @BeforeEach
    fun setUp() {
        authorModelMapper = AuthorModelMapperImpl()
    }

    @Test
    fun `WHEN map AuthorEntity THEN return AuthorModel`() {
        // GIVEN

        // WHEN
        val scenario = authorModelMapper.to(ScenarioDtoMockFactory.authorDto)

        // THEN
        assertEquals(scenario, ScenarioModelMockFactory.authorModel)
    }
}
