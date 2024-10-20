package com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.CharacterModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.CharacterDto

internal class CharacterModelMapper(
    private val descriptionModelMapper: DescriptionModelMapper,
) {

    fun toDomain(from: CharacterDto) = CharacterModel(
        name = from.name,
        description = from.description.let(descriptionModelMapper::toDomain),
    )
}
