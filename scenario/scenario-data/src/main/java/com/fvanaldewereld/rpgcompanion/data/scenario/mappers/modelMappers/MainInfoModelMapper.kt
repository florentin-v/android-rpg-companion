package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.MainInfoModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.ScenarioDto
import org.koin.core.context.GlobalContext

interface MainInfoModelMapper : ModelMapper<ScenarioDto, MainInfoModel>

internal class MainInfoModelMapperImpl : MainInfoModelMapper {
    private val authorModeMapper: AuthorModelMapper by GlobalContext.get().inject()
    private val informationModelMapper: InformationModelMapper by GlobalContext.get().inject()
    private val summaryModelMapper: SummaryModelMapper by GlobalContext.get().inject()
    private val titleModelMapper: TitleModelMapper by GlobalContext.get().inject()

    override fun to(from: ScenarioDto) = MainInfoModel(
        author = from.author.let { authorModeMapper.to(from = it) },
        summary = from.summary.let { summaryModelMapper.to(from = it) },
        title = from.title.let { titleModelMapper.to(from = it) },
        information = informationModelMapper.to(from = from.information),
    )
}
