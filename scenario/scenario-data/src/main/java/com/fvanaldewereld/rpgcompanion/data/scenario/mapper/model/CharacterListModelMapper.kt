package com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.CharacterListModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.CharacterListDto

internal class CharacterListModelMapper(
    private val characterModelMapper: CharacterModelMapper,
) {

    fun toDomain(from: CharacterListDto?) = CharacterListModel(
        list = from?.list.orEmpty().map(characterModelMapper::toDomain),
    )
}
