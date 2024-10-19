package com.fvanaldewereld.rpgcompanion.data.character.repositories

import com.fvanaldewereld.rpgcompanion.api.domain.character.models.CharacterModel
import com.fvanaldewereld.rpgcompanion.api.domain.character.repositories.DbCharacterRepository
import com.fvanaldewereld.rpgcompanion.data.character.mappers.CharacterMapper
import com.fvanaldewereld.rpgcompanion.data.character.sources.localDatabase.dao.CharacterDao
import org.koin.core.context.GlobalContext

class LocalDbCharacterRepositoryImpl : DbCharacterRepository {

    private val characterDao: CharacterDao by GlobalContext.get().inject()
    private val characterMapper: CharacterMapper by GlobalContext.get().inject()

    override suspend fun addCharacter(characterModel: CharacterModel) = characterDao
        .insert(
            character = characterMapper.from(to = characterModel),
        )

    override suspend fun getAllCharacterList(): List<CharacterModel> = characterDao
        .getAllCharacterList()
        .map(characterMapper::to)

    override suspend fun getAllCharacterListByGameId(gameId: Long): List<CharacterModel> = characterDao
        .getAllCharacterListByGameId(gameId = gameId)
        .map(characterMapper::to)

    override suspend fun getLastCharacterList(number: Int): List<CharacterModel> = characterDao
        .getLastCharacterList(number = number)
        .map(characterMapper::to)

    override suspend fun getCharacterById(characterId: Long): CharacterModel = characterDao
        .getCharacterById(characterId = characterId)
        .let(characterMapper::to)

    override suspend fun deleteById(id: Long) = characterDao
        .delete(
            character = characterDao.getCharacterById(characterId = id),
        )

}
