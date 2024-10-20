package com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.GenreListModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.InformationModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ThemeListModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.InformationDto

internal class InformationModelMapper {

    fun toDomain(from: InformationDto?) = InformationModel(
        genreList = from?.genreList.orEmpty().let(::GenreListModel),
        nbPlayers = from?.nbPlayers ?: 1,
        themeList = from?.themeList.orEmpty().let(::ThemeListModel),
    )
}
