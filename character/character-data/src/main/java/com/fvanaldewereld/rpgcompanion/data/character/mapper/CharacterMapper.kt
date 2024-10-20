package com.fvanaldewereld.rpgcompanion.data.character.mapper

import com.fvanaldewereld.rpgcompanion.api.domain.character.model.CharacterModel
import com.fvanaldewereld.rpgcompanion.data.character.source.localDb.entity.CharacterLocalDb

internal class CharacterMapper {

    fun toDomain(from: CharacterLocalDb) = CharacterModel(
        id = from.id,
        name = from.name,
        level = from.level,
        gameId = from.gameId,
    )

    fun toDataDbObject(from: CharacterModel) = CharacterLocalDb(
        id = from.id,
        name = from.name,
        level = from.level,
        gameId = from.gameId,
    )
}
