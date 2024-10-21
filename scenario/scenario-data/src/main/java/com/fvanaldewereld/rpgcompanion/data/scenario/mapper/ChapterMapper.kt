package com.fvanaldewereld.rpgcompanion.data.scenario.mapper

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ChapterModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.DescriptionModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.ChapterDto
import com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.entity.ChapterLocalDb

/**
 * Mapper for converting between different data models related to Chapter.
 *
 * @param descriptionMapper Mapper for description related data models.
 */
internal class ChapterMapper(
    private val descriptionMapper: DescriptionMapper,
) {

    /**
     * Converts a [ChapterLocalDb] to a [ChapterModel].
     *
     * The domain model [ChapterModel] is created by mapping the name and description fields of the [ChapterLocalDb].
     *
     * @param from The [ChapterLocalDb] to convert.
     * @return The converted [ChapterModel].
     */
    fun toDomain(from: ChapterLocalDb) = ChapterModel(
        name = from.name,
        description = DescriptionModel(
            paragraphs = from.description,
        ),
    )

    /**
     * Converts a [ChapterDto] to a [ChapterModel]
     *
     * The domain model [ChapterModel] is created by mapping the name and description fields of the [ChapterDto].
     *
     * @param from The [ChapterDto] to convert.
     * @return The converted [ChapterModel].
     */
    fun toDomain(from: ChapterDto) = ChapterModel(
        name = from.name,
        description = from.description.let(descriptionMapper::toDomain),
    )

    /**
     * Converts a [ChapterModel] to a [ChapterLocalDb].
     *
     * The local database model [ChapterLocalDb] is created by mapping the name
     * and description fields of the [ChapterModel].
     *
     * @param from The [ChapterModel] to convert.
     * @return The converted [ChapterLocalDb].
     */
    fun toDataLocalDb(from: ChapterModel) = ChapterLocalDb(
        name = from.name,
        description = from.description.paragraphs,
    )
}
