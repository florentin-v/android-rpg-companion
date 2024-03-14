package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.DescriptionModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.DescriptionDto

interface DescriptionModelMapper : ModelMapper<DescriptionDto?, DescriptionModel>

internal class DescriptionModelMapperImpl : DescriptionModelMapper {
    override fun to(from: DescriptionDto?) = from?.paragraphs?.let { paragraphs ->
        DescriptionModel(paragraphs = paragraphs)
    } ?: DescriptionModel()
}
