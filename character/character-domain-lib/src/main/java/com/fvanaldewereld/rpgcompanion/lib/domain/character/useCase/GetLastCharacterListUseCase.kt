package com.fvanaldewereld.rpgcompanion.lib.domain.character.useCase

import com.fvanaldewereld.rpgcompanion.api.domain.character.model.CharacterModel
import com.fvanaldewereld.rpgcompanion.api.domain.character.repository.DbCharacterRepository

class GetLastCharacterListUseCase(
    private val localDbCharacterRepository: DbCharacterRepository,
) {

    suspend operator fun invoke(number: Int): List<CharacterModel> =
        localDbCharacterRepository.getLastCharacterList(number = number)
}
