package com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.SummaryModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.SummaryDto

internal class SummaryModelMapper(
    private val descriptionModelMapper: DescriptionModelMapper,
) {

    fun toDomain(from: SummaryDto?) = SummaryModel(text = from?.text.let(descriptionModelMapper::toDomain))
}
