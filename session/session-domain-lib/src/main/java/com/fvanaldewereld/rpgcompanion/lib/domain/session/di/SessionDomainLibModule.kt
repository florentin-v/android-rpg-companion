package com.fvanaldewereld.rpgcompanion.lib.domain.session.di

import com.fvanaldewereld.rpgcompanion.lib.domain.session.useCase.AddSessionUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.session.useCase.GetLastSessionListUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val SESSION_DOMAIN_LIB_MODULE = module {

    // Use Case
    singleOf(::AddSessionUseCase)
    singleOf(::GetLastSessionListUseCase)
}
