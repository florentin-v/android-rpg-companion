package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.CharacterModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.CharacterDto
import org.koin.core.context.GlobalContext

interface CharacterModelMapper : ModelMapper<CharacterDto, CharacterModel>

internal class CharacterModelMapperImpl : CharacterModelMapper {
    private val descriptionModelMapper: DescriptionModelMapper by GlobalContext.get().inject()

    override fun to(from: CharacterDto) = CharacterModel(
        name = from.name,
        description = descriptionModelMapper.to(from.description),
    )
}
