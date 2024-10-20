package com.fvanaldewereld.rpgcompanion.data.scenario.mapper

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.MainInfoModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.ScenarioDto

/**
 * Mapper for converting between different data models related to MainInfo.
 *
 * @param authorModeMapper Mapper for author related data models.
 * @param informationMapper Mapper for information related data models.
 * @param summaryMapper Mapper for summary related data models.
 * @param titleMapper Mapper for title related data models.
 */
internal class MainInfoMapper(
    private val authorModeMapper: AuthorMapper,
    private val informationMapper: InformationMapper,
    private val summaryMapper: SummaryMapper,
    private val titleMapper: TitleMapper,
) {

    /**
     * Converts a [ScenarioDto] to a [MainInfoModel].
     *
     * The domain model [MainInfoModel] is created by mapping the author, summary, title and information fields
     * of the [ScenarioDto].
     *
     * @param from The [ScenarioDto] to convert.
     * @return The converted [MainInfoModel].
     */
    fun toDomain(from: ScenarioDto) = MainInfoModel(
        author = from.author.let(authorModeMapper::toDomain),
        summary = from.summary.let(summaryMapper::toDomain),
        title = from.title.let(titleMapper::toDomain),
        information = from.information.let(informationMapper::toDomain),
    )

}
