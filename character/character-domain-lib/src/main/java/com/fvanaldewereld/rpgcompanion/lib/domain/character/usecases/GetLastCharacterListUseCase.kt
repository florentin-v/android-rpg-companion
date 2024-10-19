package com.fvanaldewereld.rpgcompanion.lib.domain.character.usecases

import com.fvanaldewereld.rpgcompanion.api.domain.character.models.CharacterModel
import com.fvanaldewereld.rpgcompanion.api.domain.character.repositories.DbCharacterRepository
import org.koin.core.context.GlobalContext

class GetLastCharacterListUseCase {

    private val localDbCharacterRepository: DbCharacterRepository by GlobalContext.get().inject()

    suspend operator fun invoke(number: Int): List<CharacterModel> = localDbCharacterRepository
        .getLastCharacterList(number = number)
}
