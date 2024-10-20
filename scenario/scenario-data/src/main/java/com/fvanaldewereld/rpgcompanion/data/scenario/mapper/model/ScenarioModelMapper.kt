package com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ScenarioModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.ScenarioDto

internal class ScenarioModelMapper(
    private val chapterListModelMapper: ChapterListModelMapper,
    private val characterListModelMapper: CharacterListModelMapper,
    private val placeListModelMapper: PlaceListModelMapper,
    private val mainInfoModelMapper: MainInfoModelMapper,
) {

    fun toDomain(from: ScenarioDto) = ScenarioModel(
        documentName = from.documentName,
        chapterList = from.chapterList.let(chapterListModelMapper::toDomain),
        characterList = from.characterList.let(characterListModelMapper::toDomain),
        mainInfo = from.let(mainInfoModelMapper::toDomain),
        placeList = from.placeList.let(placeListModelMapper::toDomain),
    )
}
