package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.GenresModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.InformationModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ThemesModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.InformationDto

interface InformationModelMapper : ModelMapper<InformationDto?, InformationModel?>

internal class InformationModelMapperImpl : InformationModelMapper {
    override fun to(from: InformationDto?) = InformationModel(
        genres = from?.genres?.let { GenresModel(genres = it) },
        nbPlayers = from?.nbPlayers,
        themes = from?.themes?.let { ThemesModel(themes = it) },
    )
}