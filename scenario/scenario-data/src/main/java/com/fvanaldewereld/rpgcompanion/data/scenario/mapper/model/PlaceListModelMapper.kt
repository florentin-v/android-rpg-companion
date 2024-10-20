package com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.PlaceListModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.PlaceListDto

internal class PlaceListModelMapper(
    private val placeModelMapper: PlaceModelMapper,
) {

    fun toDomain(from: PlaceListDto?) = PlaceListModel(
        list = from?.list.orEmpty().map(placeModelMapper::toDomain),
    )
}
