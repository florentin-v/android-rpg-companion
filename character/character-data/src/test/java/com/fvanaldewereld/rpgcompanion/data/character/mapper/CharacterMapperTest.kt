package com.fvanaldewereld.rpgcompanion.data.character.mapper

import com.fvanaldewereld.rpgcompanion.api.domain.character.model.CharacterModel
import com.fvanaldewereld.rpgcompanion.data.character.source.localDb.entity.CharacterLocalDb
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CharacterMapperTest {

    private lateinit var characterMapper: CharacterMapper

    @BeforeEach
    fun setUp() {
        characterMapper = CharacterMapper()
    }

    @Test
    fun `WHEN execute toDomain with characterLocalDb THEN return characterModel`() {
        // GIVEN

        // WHEN
        val characterLocalDb = CharacterLocalDb(
            id = 1,
            name = "Test Character",
            level = 10,
            gameId = 100
        )

        val characterModel = characterMapper.toDomain(characterLocalDb)

        // THEN
        val expectedCharacterModel = CharacterModel(
            id = 1,
            name = "Test Character",
            level = 10,
            gameId = 100
        )

        assertEquals(
            actual = characterModel,
            expected = expectedCharacterModel,
        )
    }

    @Test
    fun `WHEN execute toDataLocalDb with characterModel THEN return characterLocalDb`() {
        // GIVEN

        // WHEN
        val characterModel = CharacterModel(
            id = 1,
            name = "Test Character",
            level = 10,
            gameId = 100
        )

        val characterLocalDb = characterMapper.toDataLocalDb(characterModel)

        // THEN
        val expectedCharacterLocalDb = CharacterLocalDb(
            id = 1,
            name = "Test Character",
            level = 10,
            gameId = 100
        )

        assertEquals(
            actual = characterLocalDb,
            expected = expectedCharacterLocalDb,
        )
    }
}
