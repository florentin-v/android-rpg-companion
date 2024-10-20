package com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.AuthorModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.AuthorDto

internal class AuthorModelMapper {

    fun toDomain(from: AuthorDto?) = AuthorModel(name = from?.name)
}
