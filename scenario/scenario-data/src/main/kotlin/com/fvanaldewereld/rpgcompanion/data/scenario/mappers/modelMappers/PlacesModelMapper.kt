package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.PlacesModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.PlacesDto
import org.koin.core.context.GlobalContext

interface PlacesModelMapper : ModelMapper<PlacesDto?, PlacesModel>

internal class PlacesModelMapperImpl : PlacesModelMapper {
    private val placeModelMapper: PlaceModelMapper by GlobalContext.get().inject()

    override fun to(from: PlacesDto?) = PlacesModel(
        places = from?.places?.map { placeEntity ->
            placeModelMapper.to(placeEntity)
        },
    )
}
