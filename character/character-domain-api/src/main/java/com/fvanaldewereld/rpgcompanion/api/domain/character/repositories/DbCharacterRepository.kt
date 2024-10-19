package com.fvanaldewereld.rpgcompanion.api.domain.character.repositories

import com.fvanaldewereld.rpgcompanion.api.domain.character.models.CharacterModel

interface DbCharacterRepository {

    suspend fun addCharacter(characterModel: CharacterModel)

    suspend fun getAllCharacterList(): List<CharacterModel>

    suspend fun getAllCharacterListByGameId(gameId: Long): List<CharacterModel>

    suspend fun getLastCharacterList(number: Int): List<CharacterModel>

    suspend fun getCharacterById(id: Long): CharacterModel

    suspend fun deleteById(id: Long)
}
