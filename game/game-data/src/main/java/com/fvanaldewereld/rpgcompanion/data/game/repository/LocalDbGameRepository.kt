package com.fvanaldewereld.rpgcompanion.data.game.repository

import com.fvanaldewereld.rpgcompanion.api.domain.game.model.GameModel
import com.fvanaldewereld.rpgcompanion.api.domain.game.repository.DbGameRepository
import com.fvanaldewereld.rpgcompanion.data.game.mapper.GameMapper
import com.fvanaldewereld.rpgcompanion.data.game.source.localDb.dao.GameDao

internal class LocalDbGameRepository(
    private val gameDao: GameDao,
    private val gameMapper: GameMapper,
) : DbGameRepository {

    override suspend fun addGame(gameModel: GameModel) = gameDao
        .insert(
            gameLocalDb = gameMapper.toDataLocalDb(from = gameModel),
        )

    override suspend fun getAllGameList(): List<GameModel> = gameDao
        .getAllGameList()
        .map(gameMapper::toDomain)

    override suspend fun getLastGameList(number: Int): List<GameModel> = gameDao
        .getLastGameList(number = number)
        .map(gameMapper::toDomain)

    override suspend fun getGameById(gameId: Long): GameModel = gameDao
        .getGameById(gameId = gameId)
        .let(gameMapper::toDomain)

    override suspend fun deleteByGameId(gameId: Long) = gameDao
        .delete(
            gameLocalDb = gameDao.getGameById(gameId = gameId),
        )
}
