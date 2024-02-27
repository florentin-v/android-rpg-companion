package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.dbObjectMappers

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.CharacterModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.DescriptionModel
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.entities.Character

interface CharacterMapper : DbObjectMapper<Character, CharacterModel>

internal class CharacterMapperImpl : CharacterMapper {

    override fun to(from: Character) = CharacterModel(
        name = from.name,
        description = DescriptionModel(
            paragraphs = from.description,
        ),
    )

    override fun from(to: CharacterModel) = Character(
        name = to.name,
        description = to.description?.paragraphs,
    )
}
