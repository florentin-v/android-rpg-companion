package com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ChapterModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.ChapterDto

internal class ChapterModelMapper(
    private val descriptionModelMapper: DescriptionModelMapper,
) {

    fun toDomain(from: ChapterDto) = ChapterModel(
        name = from.name,
        description = from.description.let(descriptionModelMapper::toDomain),
    )
}
