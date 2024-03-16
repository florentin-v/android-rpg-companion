package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.PlaceModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.PlaceDto
import org.koin.core.context.GlobalContext

interface PlaceModelMapper : ModelMapper<PlaceDto, PlaceModel>

internal class PlaceModelMapperImpl : PlaceModelMapper {
    private val descriptionModelMapper: DescriptionModelMapper by GlobalContext.get().inject()

    override fun to(from: PlaceDto) = PlaceModel(
        name = from.name,
        description = descriptionModelMapper.to(from.description),
    )
}
