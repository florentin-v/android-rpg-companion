package com.fvanaldewereld.rpgcompanion.data.game.mapper

import com.fvanaldewereld.rpgcompanion.api.domain.game.model.GameModel
import com.fvanaldewereld.rpgcompanion.data.game.source.localDb.entity.GameLocalDb

internal class GameMapper {

    fun toDomain(from: GameLocalDb) =
        GameModel(
            id = from.id,
            name = from.name,
        )

    fun toDataLocalDb(from: GameModel) = GameLocalDb(
        id = from.id,
        name = from.name,
    )
}
