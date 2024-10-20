package com.fvanaldewereld.rpgcompanion.data.scenario.mapper.localDb

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.CharacterModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.DescriptionModel
import com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.entity.CharacterLocalDb

internal class CharacterMapper {

    fun toDomain(from: CharacterLocalDb) = CharacterModel(
        name = from.name,
        description = DescriptionModel(
            paragraphs = from.description,
        ),
    )

    fun toDataLocalDb(from: CharacterModel) = CharacterLocalDb(
        name = from.name,
        description = from.description.paragraphs,
    )
}
