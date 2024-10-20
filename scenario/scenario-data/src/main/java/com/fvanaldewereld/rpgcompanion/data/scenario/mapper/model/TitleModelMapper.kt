package com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.TitleModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.TitleDto

internal class TitleModelMapper {

    fun toDomain(from: TitleDto?) = TitleModel(value = from?.value)
}
