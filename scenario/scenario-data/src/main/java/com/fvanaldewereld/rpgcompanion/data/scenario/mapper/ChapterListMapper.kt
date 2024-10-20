package com.fvanaldewereld.rpgcompanion.data.scenario.mapper

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ChapterListModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.ChapterListDto

/**
 * Mapper for converting between different data models related to a Chapter list.
 *
 * @param chapterMapper Mapper for chapter list related data models.
 */
internal class ChapterListMapper(
    private val chapterMapper: ChapterMapper,
) {

    /**
     * Converts a [ChapterListDto] to a [ChapterListModel].
     *
     * The domain model [ChapterListModel] is created by mapping the list field of the [ChapterListDto].
     *
     * @param from The [ChapterListDto] to convert.
     * @return The converted [ChapterListModel].
     */
    fun toDomain(from: ChapterListDto?) = ChapterListModel(
        list = from?.list.orEmpty().map(chapterMapper::toDomain),
    )
}
