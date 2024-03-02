package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.dbObjectMappers

import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDbObjectMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CharacterMapperTest {

    private lateinit var characterMapper: CharacterMapper

    @BeforeEach
    fun setUp() {
        characterMapper = CharacterMapperImpl()
    }

    @Test
    fun `WHEN map Character THEN return CharacterModel`() {
        // GIVEN

        // WHEN
        val scenario = characterMapper.to(ScenarioDbObjectMockFactory.character1)

        // THEN
        Assertions.assertEquals(scenario, ScenarioModelMockFactory.characterModel1)
    }

    @Test
    fun `WHEN map CharacterModel THEN return Character`() {
        // GIVEN

        // WHEN
        val scenario = characterMapper.from(ScenarioModelMockFactory.characterModel1)

        // THEN
        Assertions.assertEquals(scenario, ScenarioDbObjectMockFactory.character1)
    }
}
