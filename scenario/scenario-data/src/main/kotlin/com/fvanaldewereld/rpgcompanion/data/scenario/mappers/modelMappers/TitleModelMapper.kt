package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.TitleModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.TitleDto

interface TitleModelMapper : ModelMapper<TitleDto?, TitleModel>

internal class TitleModelMapperImpl : TitleModelMapper {
    override fun to(from: TitleDto?) = TitleModel(title = from?.value)
}
