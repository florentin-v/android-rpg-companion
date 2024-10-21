package com.fvanaldewereld.rpgcompanion.lib.domain.game.useCase

import com.fvanaldewereld.rpgcompanion.api.domain.game.model.GameModel
import com.fvanaldewereld.rpgcompanion.api.domain.game.repository.DbGameRepository

class GetGameByIdUseCase(
    private val localDbGameRepository: DbGameRepository,
) {

    suspend operator fun invoke(gameId: Long): GameModel =
        localDbGameRepository.getGameById(gameId = gameId)
}
