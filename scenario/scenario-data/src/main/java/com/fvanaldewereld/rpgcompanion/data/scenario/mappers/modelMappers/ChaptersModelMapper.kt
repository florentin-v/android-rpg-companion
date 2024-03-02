package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ChaptersModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.ChaptersDto
import org.koin.core.context.GlobalContext

interface ChaptersModelMapper : ModelMapper<ChaptersDto?, ChaptersModel>

internal class ChaptersModelMapperImpl : ChaptersModelMapper {
    private val chapterModelMapper: ChapterModelMapper by GlobalContext.get().inject()

    override fun to(from: ChaptersDto?) = ChaptersModel(
        chapters = from?.chapters?.map { chapterEntity -> chapterModelMapper.to(chapterEntity) }
    )
}
