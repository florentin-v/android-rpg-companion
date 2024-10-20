package com.fvanaldewereld.rpgcompanion.data.scenario.mapper

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.GenreListModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.InformationModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ThemeListModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.InformationDto
import com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.embedded.InformationLocalDb
import kotlin.collections.orEmpty

/**
 * Mapper for converting between different data models related to Information.
 */
internal class InformationMapper {

    /**
     * Converts a [InformationLocalDb] to a [InformationModel].
     *
     * The domain model [InformationModel] is a simple wrapper around a list of genres,
     * a number of players and a list of themes.
     *
     * @param from The [InformationLocalDb] to convert.
     * @return The converted [InformationModel].
     */
    fun toDomain(from: InformationLocalDb) = InformationModel(
        genreList = GenreListModel(values = from.genreList),
        nbPlayers = from.nbPlayers,
        themeList = ThemeListModel(values = from.themeList),
    )

    /**
     * Converts an [InformationDto] to an [InformationModel].
     *
     * The domain model [InformationModel] is created by mapping the genreList,
     * nbPlayers and themeList fields of the [InformationDto].
     *
     * @param from The [InformationDto] to convert.
     * @return The converted [InformationModel].
     */
    fun toDomain(from: InformationDto?) = InformationModel(
        genreList = from?.genreList.orEmpty().let(::GenreListModel),
        nbPlayers = from?.nbPlayers ?: 1,
        themeList = from?.themeList.orEmpty().let(::ThemeListModel),
    )

    /**
     * Converts an [InformationModel] to an [InformationLocalDb].
     *
     * The local database model [InformationLocalDb] is created by mapping the genreList,
     * nbPlayers and themeList fields of the [InformationModel].
     *
     * @param from The [InformationModel] to convert.
     * @return The converted [InformationLocalDb].
     */
    fun toDataLocalDb(from: InformationModel) = InformationLocalDb(
        genreList = from.genreList.values,
        nbPlayers = from.nbPlayers,
        themeList = from.themeList.values,
    )
}
