package com.fvanaldewereld.rpgcompanion.lib.domain.game.di

import com.fvanaldewereld.rpgcompanion.lib.domain.game.useCase.AddGameUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.game.useCase.GetGameByIdUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.game.useCase.GetLastGameListUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val GAME_DOMAIN_LIB_MODULE = module {

    // Use Case
    singleOf(::AddGameUseCase)
    singleOf(::GetGameByIdUseCase)
    singleOf(::GetLastGameListUseCase)
}
