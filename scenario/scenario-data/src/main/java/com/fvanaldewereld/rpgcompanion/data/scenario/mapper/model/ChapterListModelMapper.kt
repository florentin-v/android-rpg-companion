package com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ChapterListModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.ChapterListDto

internal class ChapterListModelMapper(
    private val chapterModelMapper: ChapterModelMapper,
) {

    fun toDomain(from: ChapterListDto?) = ChapterListModel(
        list = from?.list.orEmpty().map(chapterModelMapper::toDomain),
    )
}
