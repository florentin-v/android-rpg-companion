package com.fvanaldewereld.rpgcompanion.lib.domain.game.useCase

import com.fvanaldewereld.rpgcompanion.api.domain.game.model.GameModel
import com.fvanaldewereld.rpgcompanion.api.domain.game.repository.DbGameRepository

class GetLastGameListUseCase(
    private val localDbGameRepository: DbGameRepository,
) {

    suspend operator fun invoke(number: Int): List<GameModel> =
        localDbGameRepository.getLastGameList(number = number)
}
