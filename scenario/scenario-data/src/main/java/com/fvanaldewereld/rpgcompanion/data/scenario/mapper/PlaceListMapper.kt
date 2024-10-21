package com.fvanaldewereld.rpgcompanion.data.scenario.mapper

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.PlaceListModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.PlaceListDto

/**
 * Mapper for converting between different data models related to a Place list.
 *
 * @param placeMapper Mapper for place list related data models.
 */
internal class PlaceListMapper(
    private val placeMapper: PlaceMapper,
) {

    /**
     * Converts a [PlaceListDto] to a [PlaceListModel].
     *
     * The domain model [PlaceListModel] is created by mapping the list field of the [PlaceListDto].
     *
     * @param from The [PlaceListDto] to convert.
     * @return The converted [PlaceListModel].
     */
    fun toDomain(from: PlaceListDto?) = PlaceListModel(
        list = from?.list.orEmpty().map(placeMapper::toDomain),
    )
}
