package com.fvanaldewereld.rpgcompanion.api.domain.character.repository

import com.fvanaldewereld.rpgcompanion.api.domain.character.model.CharacterModel

interface DbCharacterRepository {

    suspend fun addCharacter(characterModel: CharacterModel)

    suspend fun getAllCharacterList(): List<CharacterModel>

    suspend fun getAllCharacterListByGameId(gameId: Long): List<CharacterModel>

    suspend fun getLastCharacterList(number: Int): List<CharacterModel>

    suspend fun getCharacterById(characterId: Long): CharacterModel

    suspend fun deleteByCharacterId(characterId: Long)
}
