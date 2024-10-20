package com.fvanaldewereld.rpgcompanion.data.scenario.mapper

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.CharacterListModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.CharacterListDto

/**
 * Mapper for converting between different data models related to a Character list.
 *
 * @param characterMapper Mapper for character list related data models.
 */
internal class CharacterListMapper(
    private val characterMapper: CharacterMapper,
) {

    /**
     * Converts a [CharacterListDto] to a [CharacterListModel].
     *
     * The domain model [CharacterListModel] is created by mapping the list field of the [CharacterListDto].
     *
     * @param from The [CharacterListDto] to convert.
     * @return The converted [CharacterListModel].
     */
    fun toDomain(from: CharacterListDto?) = CharacterListModel(
        list = from?.list.orEmpty().map(characterMapper::toDomain),
    )
}
