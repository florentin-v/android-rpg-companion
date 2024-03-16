package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.CharactersModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.CharactersDto
import org.koin.core.context.GlobalContext

interface CharactersModelMapper : ModelMapper<CharactersDto?, CharactersModel>

internal class CharactersModelMapperImpl : CharactersModelMapper {
    private val characterModelMapper: CharacterModelMapper by GlobalContext.get().inject()

    override fun to(from: CharactersDto?) = CharactersModel(
        characters = from?.characters?.map { characterDto ->
            characterModelMapper.to(characterDto)
        }.orEmpty(),
    )
}
