package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.dtoMappers

import com.fvanaldewereld.rpgcompanion.data.scenario.dto.Dto

fun interface DtoMapper<FROM, TO : Dto> {

    fun to(from: FROM): TO
}
