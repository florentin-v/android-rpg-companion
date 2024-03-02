package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ScenarioModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.ScenarioDto
import org.koin.core.context.GlobalContext

interface ScenarioModelMapper : ModelMapper<ScenarioDto, ScenarioModel>

internal class ScenarioModelMapperImpl : ScenarioModelMapper {
    private val authorModeMapper: AuthorModelMapper by GlobalContext.get().inject()
    private val chaptersModelMapper: ChaptersModelMapper by GlobalContext.get().inject()
    private val charactersModelMapper: CharactersModelMapper by GlobalContext.get().inject()
    private val informationModelMapper: InformationModelMapper by GlobalContext.get().inject()
    private val placesModelMapper: PlacesModelMapper by GlobalContext.get().inject()
    private val summaryModelMapper: SummaryModelMapper by GlobalContext.get().inject()
    private val titleModelMapper: TitleModelMapper by GlobalContext.get().inject()

    override fun to(from: ScenarioDto) = ScenarioModel(
        author = authorModeMapper.to(from.author),
        characters = charactersModelMapper.to(from.characters),
        chapters = chaptersModelMapper.to(from.chapters),
        documentName = from.documentName,
        information = informationModelMapper.to(from.information),
        places = placesModelMapper.to(from.places),
        summary = summaryModelMapper.to(from.summary),
        title = titleModelMapper.to(from.title),
    )
}
