package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.Model
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.Dto

fun interface ModelMapper<FROM : Dto?, TO : Model?> {

    fun to(from: FROM): TO
}
