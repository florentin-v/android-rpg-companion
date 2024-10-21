package com.fvanaldewereld.rpgcompanion.data.scenario.mapper

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.CharacterModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.DescriptionModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.CharacterDto
import com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.entity.CharacterLocalDb

/**
 * Mapper for converting between different data models related to Character.
 *
 * @param descriptionMapper Mapper for description related data models.
 */
internal class CharacterMapper(
    private val descriptionMapper: DescriptionMapper,
) {

    /**
     * Converts a [CharacterLocalDb] to a [CharacterModel].
     *
     * The domain model [CharacterModel] is created by mapping the name
     * and description fields of the [CharacterLocalDb].
     *
     * @param from The [CharacterLocalDb] to convert.
     * @return The converted [CharacterModel].
     */
    fun toDomain(from: CharacterLocalDb) = CharacterModel(
        name = from.name,
        description = DescriptionModel(
            paragraphs = from.description,
        ),
    )

    /**
     * Converts a [CharacterDto] to a [CharacterModel].
     *
     * The domain model [CharacterModel] is created by mapping the name and description fields of the [CharacterDto].
     *
     * @param from The [CharacterDto] to convert.
     * @return The converted [CharacterModel].
     */
    fun toDomain(from: CharacterDto) = CharacterModel(
        name = from.name,
        description = from.description.let(descriptionMapper::toDomain),
    )

    /**
     * Converts a [CharacterModel] to a [CharacterLocalDb].
     *
     * The local database model [CharacterLocalDb] is created by mapping the name
     * and description fields of the [CharacterModel].
     *
     * @param from The [CharacterModel] to convert.
     * @return The converted [CharacterLocalDb].
     */
    fun toDataLocalDb(from: CharacterModel) = CharacterLocalDb(
        name = from.name,
        description = from.description.paragraphs,
    )
}
