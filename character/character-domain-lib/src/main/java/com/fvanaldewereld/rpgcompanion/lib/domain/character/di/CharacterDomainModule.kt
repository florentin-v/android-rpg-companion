package com.fvanaldewereld.rpgcompanion.lib.domain.character.di

import com.fvanaldewereld.rpgcompanion.lib.domain.character.usecases.AddCharacterUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.character.usecases.GetLastCharacterListUseCase
import org.koin.dsl.module

val CHARACTER_DOMAIN_LIB_MODULE = module {

    // Usecases
    single { AddCharacterUseCase() }
    single { GetLastCharacterListUseCase() }
}
