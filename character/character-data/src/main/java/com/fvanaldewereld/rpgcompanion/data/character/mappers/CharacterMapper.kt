package com.fvanaldewereld.rpgcompanion.data.character.mappers

import com.fvanaldewereld.rpgcompanion.api.domain.character.models.CharacterModel
import com.fvanaldewereld.rpgcompanion.data.character.sources.localDatabase.entities.Character

interface CharacterMapper : DbObjectMapper<Character, CharacterModel>

internal class CharacterMapperImpl : CharacterMapper {

    override fun to(from: Character) = CharacterModel(
        id = from.id,
        name = from.name,
        level = from.level,
        gameId = from.gameId,
    )

    override fun from(to: CharacterModel) = Character(
        id = to.id,
        name = to.name,
        level = to.level,
        gameId = to.gameId,
    )
}
