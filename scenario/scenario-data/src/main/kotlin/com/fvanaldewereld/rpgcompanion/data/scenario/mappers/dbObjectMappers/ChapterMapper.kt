package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.dbObjectMappers

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ChapterModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.DescriptionModel
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.entities.Chapter

interface ChapterMapper : DbObjectMapper<Chapter, ChapterModel>

internal class ChapterMapperImpl : ChapterMapper {

    override fun to(from: Chapter) = ChapterModel(
        name = from.name,
        description = DescriptionModel(
            paragraphs = from.description,
        ),
    )

    override fun from(to: ChapterModel) = Chapter(
        name = to.name,
        description = to.description?.paragraphs
    )
}
