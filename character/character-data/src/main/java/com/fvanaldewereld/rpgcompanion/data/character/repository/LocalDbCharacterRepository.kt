package com.fvanaldewereld.rpgcompanion.data.character.repository

import com.fvanaldewereld.rpgcompanion.api.domain.character.model.CharacterModel
import com.fvanaldewereld.rpgcompanion.api.domain.character.repository.DbCharacterRepository
import com.fvanaldewereld.rpgcompanion.data.character.mapper.CharacterMapper
import com.fvanaldewereld.rpgcompanion.data.character.source.localDb.dao.CharacterDao

internal class LocalDbCharacterRepository(
    private val characterDao: CharacterDao,
    private val characterMapper: CharacterMapper,
) : DbCharacterRepository {

    override suspend fun addCharacter(characterModel: CharacterModel) = characterDao
        .insert(
            characterLocalDb = characterMapper.toDataLocalDb(from = characterModel),
        )

    override suspend fun getAllCharacterList(): List<CharacterModel> = characterDao
        .getAllCharacterList()
        .map(characterMapper::toDomain)

    override suspend fun getAllCharacterListByGameId(gameId: Long): List<CharacterModel> = characterDao
        .getAllCharacterListByGameId(gameId = gameId)
        .map(characterMapper::toDomain)

    override suspend fun getLastCharacterList(number: Int): List<CharacterModel> = characterDao
        .getLastCharacterList(number = number)
        .map(characterMapper::toDomain)

    override suspend fun getCharacterById(characterId: Long): CharacterModel = characterDao
        .getCharacterById(characterId = characterId)
        .let(characterMapper::toDomain)

    override suspend fun deleteByCharacterId(characterId: Long) = characterDao
        .delete(
            characterLocalDb = characterDao.getCharacterById(characterId = characterId),
        )
}
