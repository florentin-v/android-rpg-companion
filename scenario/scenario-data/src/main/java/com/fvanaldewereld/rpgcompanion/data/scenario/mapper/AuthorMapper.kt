package com.fvanaldewereld.rpgcompanion.data.scenario.mapper

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.AuthorModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.AuthorDto


/**
 * Mapper for converting between different data models related to Author.
 */
internal class AuthorMapper {

    /**
     * Converts an [AuthorDto] to an [AuthorModel].
     *
     * The domain model [AuthorModel] is created by mapping the name field of the [AuthorDto].
     *
     * @param from The [AuthorDto] to convert.
     * @return The converted [AuthorModel].
     */
    fun toDomain(from: AuthorDto?) = AuthorModel(name = from?.name)
}
