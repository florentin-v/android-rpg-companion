package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ChapterModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.ChapterDto
import org.koin.core.context.GlobalContext

interface ChapterModelMapper : ModelMapper<ChapterDto?, ChapterModel>

internal class ChapterModelMapperImpl : ChapterModelMapper {
    private val descriptionModelMapper: DescriptionModelMapper by GlobalContext.get().inject()

    override fun to(from: ChapterDto?) = ChapterModel(
        name = from?.name,
        description = descriptionModelMapper.to(from?.description),
    )
}
