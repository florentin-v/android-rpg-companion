package com.fvanaldewereld.rpgcompanion.data.scenario.mapper

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.SummaryModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.SummaryDto

/**
 * Mapper for converting between different data models related to Summary.
 *
 * @param descriptionMapper Mapper for description related data models.
 */
internal class SummaryMapper(
    private val descriptionMapper: DescriptionMapper,
) {

    /**
     * Converts a [SummaryDto] to a [SummaryModel].
     *
     * The domain model [SummaryModel] is created by mapping the text field of the [SummaryDto].
     *
     * @param from The [SummaryDto] to convert.
     * @return The converted [SummaryModel].
     */
    fun toDomain(from: SummaryDto?) = SummaryModel(text = from?.text.let(descriptionMapper::toDomain))
}
