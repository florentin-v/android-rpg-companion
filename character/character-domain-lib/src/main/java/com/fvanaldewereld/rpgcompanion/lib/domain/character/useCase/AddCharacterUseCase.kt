package com.fvanaldewereld.rpgcompanion.lib.domain.character.useCase

import com.fvanaldewereld.rpgcompanion.api.domain.character.model.CharacterModel
import com.fvanaldewereld.rpgcompanion.api.domain.character.repository.DbCharacterRepository

class AddCharacterUseCase(
    private val localDbCharacterRepository: DbCharacterRepository,
) {

    suspend operator fun invoke(characterModel: CharacterModel) =
        localDbCharacterRepository.addCharacter(characterModel = characterModel)
}
