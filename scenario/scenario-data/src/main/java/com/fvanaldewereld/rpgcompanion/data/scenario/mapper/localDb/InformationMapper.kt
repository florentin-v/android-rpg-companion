package com.fvanaldewereld.rpgcompanion.data.scenario.mapper.localDb

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.GenreListModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.InformationModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ThemeListModel
import com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.embedded.InformationLocalDb

internal class InformationMapper {

    fun toDomain(from: InformationLocalDb) = InformationModel(
        genreList = GenreListModel(values = from.genreList),
        nbPlayers = from.nbPlayers,
        themeList = ThemeListModel(values = from.themeList),
    )

    fun toDataLocalDb(from: InformationModel) = InformationLocalDb(
        genreList = from.genreList.values,
        nbPlayers = from.nbPlayers,
        themeList = from.themeList.values,
    )
}
