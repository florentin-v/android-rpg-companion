package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ScenarioModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.ScenarioDto
import org.koin.core.context.GlobalContext

interface ScenarioModelMapper : ModelMapper<ScenarioDto, ScenarioModel>

internal class ScenarioModelMapperImpl : ScenarioModelMapper {
    private val chaptersModelMapper: ChaptersModelMapper by GlobalContext.get().inject()
    private val charactersModelMapper: CharactersModelMapper by GlobalContext.get().inject()
    private val placesModelMapper: PlacesModelMapper by GlobalContext.get().inject()
    private val mainInfoModelMapper: MainInfoModelMapper by GlobalContext.get().inject()

    override fun to(from: ScenarioDto) = ScenarioModel(
        documentName = from.documentName,
        chapters = chaptersModelMapper.to(from = from.chapters),
        characters = charactersModelMapper.to(from = from.characters),
        mainInfo = mainInfoModelMapper.to(from = from),
        places = placesModelMapper.to(from = from.places),
    )
}
