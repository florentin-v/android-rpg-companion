package com.fvanaldewereld.rpgcompanion.lib.domain.character.usecases

import com.fvanaldewereld.rpgcompanion.api.domain.character.models.CharacterModel
import com.fvanaldewereld.rpgcompanion.api.domain.character.repositories.DbCharacterRepository
import org.koin.core.context.GlobalContext

class AddCharacterUseCase {

    private val localDbCharacterRepository: DbCharacterRepository by GlobalContext.get().inject()

    suspend operator fun invoke(characterModel: CharacterModel) = localDbCharacterRepository
        .addCharacter(characterModel = characterModel)
}
