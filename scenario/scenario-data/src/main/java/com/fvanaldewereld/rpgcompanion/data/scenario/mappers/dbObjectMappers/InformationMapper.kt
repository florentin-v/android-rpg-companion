package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.dbObjectMappers

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.GenresModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.InformationModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ThemesModel
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.embedded.Information

interface InformationMapper : DbObjectMapper<Information, InformationModel>

internal class InformationMapperImpl : InformationMapper {

    override fun to(from: Information) = InformationModel(
        genres = GenresModel(values = from.genres),
        nbPlayers = from.nbPlayers,
        themes = ThemesModel(from.themes),
    )

    override fun from(to: InformationModel) = Information(
        genres = to.genres.values,
        nbPlayers = to.nbPlayers,
        themes = to.themes.values,
    )
}
