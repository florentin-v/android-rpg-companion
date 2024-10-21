package com.fvanaldewereld.rpgcompanion.api.domain.game.repository

import com.fvanaldewereld.rpgcompanion.api.domain.game.model.GameModel

interface DbGameRepository {

    suspend fun addGame(gameModel: GameModel)

    suspend fun getAllGameList(): List<GameModel>

    suspend fun getLastGameList(number: Int): List<GameModel>

    suspend fun getGameById(gameId: Long): GameModel

    suspend fun deleteByGameId(gameId: Long)
}
