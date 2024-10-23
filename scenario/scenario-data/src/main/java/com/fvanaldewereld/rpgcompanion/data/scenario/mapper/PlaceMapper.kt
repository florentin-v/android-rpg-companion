package com.fvanaldewereld.rpgcompanion.data.scenario.mapper

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.DescriptionModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.PlaceModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.PlaceDto
import com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.entity.PlaceLocalDb

/**
 * Mapper for converting between different data models related to Place.
 *
 * @param descriptionMapper Mapper for description related data models.
 */
internal class PlaceMapper(
    private val descriptionMapper: DescriptionMapper,
) {

    /**
     * Converts a [PlaceLocalDb] to a [PlaceModel].
     *
     * The domain model [PlaceModel] is created by mapping the name and description fields of the [PlaceLocalDb].
     *
     * @param from The [PlaceLocalDb] to convert.
     * @return The converted [PlaceModel].
     */
    fun toDomain(from: PlaceLocalDb) = PlaceModel(
        name = from.name,
        description = DescriptionModel(
            paragraphs = from.description,
        ),
    )

    /**
     * Converts a [PlaceDto] to a [PlaceModel].
     *
     * The domain model [PlaceModel] is created by mapping the name and description fields of the [PlaceDto].
     *
     * @param from The [PlaceDto] to convert.
     * @return The converted [PlaceModel].
     */
    fun toDomain(from: PlaceDto) = PlaceModel(
        name = from.name,
        description = from.description.let(descriptionMapper::toDomain),
    )

    /**
     * Converts a [PlaceModel] to a [PlaceLocalDb].
     *
     * The local database model [PlaceLocalDb] is created by mapping the name
     * and description fields of the [PlaceModel].
     *
     * @param from The [PlaceModel] to convert.
     * @return The converted [PlaceLocalDb].
     */
    fun toDataLocalDb(from: PlaceModel) = PlaceLocalDb(
        name = from.name,
        description = from.description.paragraphs,
    )
}
