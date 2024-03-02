package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.dbObjectMappers

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.GenresModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.InformationModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ThemesModel
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.embedded.Information

interface InformationMapper : DbObjectMapper<Information, InformationModel>

internal class InformationMapperImpl : InformationMapper {

    override fun to(from: Information) = InformationModel(
        genres = from.genres?.let { GenresModel(genres = it) },
        nbPlayers = from.nbPlayers,
        themes = from.themes?.let { ThemesModel(it) },
    )

    override fun from(to: InformationModel) = Information(
        genres = to.genres?.genres,
        nbPlayers = to.nbPlayers,
        themes = to.themes?.themes,
    )
}
