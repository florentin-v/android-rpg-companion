package com.fvanaldewereld.rpgcompanion.data.scenario.mapper

import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDtoMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AuthorMapperTest {

    private lateinit var authorMapper: AuthorMapper

    @BeforeEach
    fun setUp() {
        authorMapper = AuthorMapper()
    }

    @Test
    fun `WHEN map AuthorDto THEN return AuthorModel`() {
        // GIVEN

        // WHEN
        val scenario = authorMapper.toDomain(ScenarioDtoMockFactory.authorDto)

        // THEN
        assertEquals(
            actual = scenario,
            expected = ScenarioModelMockFactory.authorModel,
        )
    }
}
