package com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.PlaceModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.PlaceDto

internal class PlaceModelMapper(
    private val descriptionModelMapper: DescriptionModelMapper,
) {

    fun toDomain(from: PlaceDto) = PlaceModel(
        name = from.name,
        description = from.description.let(descriptionModelMapper::toDomain),
    )
}
