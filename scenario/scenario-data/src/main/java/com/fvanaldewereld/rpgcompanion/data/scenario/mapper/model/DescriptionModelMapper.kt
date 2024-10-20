package com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.DescriptionModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.DescriptionDto

internal class DescriptionModelMapper {

    fun toDomain(from: DescriptionDto?) = from?.paragraphs.orEmpty().let(::DescriptionModel)
}
