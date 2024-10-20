package com.fvanaldewereld.rpgcompanion.data.scenario.mapper.localDb

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ChapterModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.DescriptionModel
import com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.entity.ChapterLocalDb

internal class ChapterMapper {

    fun toDomain(from: ChapterLocalDb) = ChapterModel(
        name = from.name,
        description = DescriptionModel(
            paragraphs = from.description,
        ),
    )

    fun toDataLocalDb(from: ChapterModel) = ChapterLocalDb(
        name = from.name,
        description = from.description.paragraphs,
    )
}
