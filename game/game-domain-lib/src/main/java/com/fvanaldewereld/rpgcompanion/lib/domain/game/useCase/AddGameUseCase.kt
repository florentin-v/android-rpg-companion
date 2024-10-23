package com.fvanaldewereld.rpgcompanion.lib.domain.game.useCase

import com.fvanaldewereld.rpgcompanion.api.domain.game.model.GameModel
import com.fvanaldewereld.rpgcompanion.api.domain.game.repository.DbGameRepository

class AddGameUseCase(
    private val localDbGameRepository: DbGameRepository,
) {

    suspend operator fun invoke(gameModel: GameModel) =
        localDbGameRepository.addGame(gameModel = gameModel)
}
