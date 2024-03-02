package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.SummaryModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.SummaryDto
import org.koin.core.context.GlobalContext

interface SummaryModelMapper : ModelMapper<SummaryDto?, SummaryModel>

internal class SummaryModelMapperImpl : SummaryModelMapper {
    private val descriptionModelMapper: DescriptionModelMapper by GlobalContext.get().inject()

    override fun to(from: SummaryDto?) = SummaryModel(text = descriptionModelMapper.to(from?.text), tt = from?.tt ?: "")
}
