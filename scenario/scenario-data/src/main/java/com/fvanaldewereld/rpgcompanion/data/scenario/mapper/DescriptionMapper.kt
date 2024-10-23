package com.fvanaldewereld.rpgcompanion.data.scenario.mapper

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.DescriptionModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.DescriptionDto

/**
 * Mapper for converting between different data models related to Description.
 */
internal class DescriptionMapper {

    /**
     * Converts a [DescriptionDto] to a [DescriptionModel].
     *
     * The domain model [DescriptionModel] is created by mapping the paragraphs field of the [DescriptionDto]
     *
     * @param from The [DescriptionDto] to convert.
     * @return The converted [DescriptionModel].
     */
    fun toDomain(from: DescriptionDto?) = from?.paragraphs.orEmpty().let(::DescriptionModel)
}
