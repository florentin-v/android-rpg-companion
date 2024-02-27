package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.dbObjectMappers

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.DescriptionModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.PlaceModel
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.entities.Place

interface PlaceMapper : DbObjectMapper<Place, PlaceModel>

internal class PlaceMapperImpl : PlaceMapper {

    override fun to(from: Place) = PlaceModel(
        name = from.name,
        description = DescriptionModel(
            paragraphs = from.description,
        ),
    )

    override fun from(to: PlaceModel) = Place(
        name = to.name,
        description = to.description?.paragraphs,
    )
}
