package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.AuthorModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.AuthorDto

interface AuthorModelMapper : ModelMapper<AuthorDto?, AuthorModel>

internal class AuthorModelMapperImpl : AuthorModelMapper {
    override fun to(from: AuthorDto?) = AuthorModel(name = from?.name)
}
