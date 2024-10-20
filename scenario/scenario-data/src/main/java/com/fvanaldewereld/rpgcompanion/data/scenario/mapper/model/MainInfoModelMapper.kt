package com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.MainInfoModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.ScenarioDto

internal class MainInfoModelMapper(
    private val authorModeMapper: AuthorModelMapper,
    private val informationModelMapper: InformationModelMapper,
    private val summaryModelMapper: SummaryModelMapper,
    private val titleModelMapper: TitleModelMapper,
) {

    fun toDomain(from: ScenarioDto) = MainInfoModel(
        author = from.author.let(authorModeMapper::toDomain),
        summary = from.summary.let(summaryModelMapper::toDomain),
        title = from.title.let(titleModelMapper::toDomain),
        information = from.information.let(informationModelMapper::toDomain),
    )
}
