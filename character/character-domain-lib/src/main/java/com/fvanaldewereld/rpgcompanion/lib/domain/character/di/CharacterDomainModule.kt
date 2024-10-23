package com.fvanaldewereld.rpgcompanion.lib.domain.character.di

import com.fvanaldewereld.rpgcompanion.lib.domain.character.useCase.AddCharacterUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.character.useCase.GetLastCharacterListUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val CHARACTER_DOMAIN_LIB_MODULE = module {

    // Use Case
    singleOf(::AddCharacterUseCase)
    singleOf(::GetLastCharacterListUseCase)
}
