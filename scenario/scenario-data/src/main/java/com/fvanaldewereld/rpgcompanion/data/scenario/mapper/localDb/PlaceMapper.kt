package com.fvanaldewereld.rpgcompanion.data.scenario.mapper.localDb

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.DescriptionModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.PlaceModel
import com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.entity.PlaceLocalDb

internal class PlaceMapper {

    fun toDomain(from: PlaceLocalDb) = PlaceModel(
        name = from.name,
        description = DescriptionModel(
            paragraphs = from.description,
        ),
    )

    fun toDataLocalDb(from: PlaceModel) = PlaceLocalDb(
        name = from.name,
        description = from.description.paragraphs,
    )
}
