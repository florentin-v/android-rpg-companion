package com.fvanaldewereld.rpgcompanion.data.scenario.mapper.localDb

import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDbObjectMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CharacterMapperTest {

    private lateinit var characterMapper: CharacterMapper

    @BeforeEach
    fun setUp() {
        characterMapper = CharacterMapper()
    }

    @Test
    fun `WHEN map Character THEN return CharacterModel`() {
        // GIVEN

        // WHEN
        val scenario = characterMapper.toDomain(ScenarioDbObjectMockFactory.characterLocalDb1)

        // THEN
        Assertions.assertEquals(scenario, ScenarioModelMockFactory.characterModel1)
    }

    @Test
    fun `WHEN map CharacterModel THEN return Character`() {
        // GIVEN

        // WHEN
        val scenario = characterMapper.toDataLocalDb(ScenarioModelMockFactory.characterModel1)

        // THEN
        Assertions.assertEquals(scenario, ScenarioDbObjectMockFactory.characterLocalDb1)
    }
}
