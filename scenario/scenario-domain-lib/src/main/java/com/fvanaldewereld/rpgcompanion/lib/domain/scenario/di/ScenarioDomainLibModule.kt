package com.fvanaldewereld.rpgcompanion.lib.domain.scenario.di

import com.fvanaldewereld.rpgcompanion.lib.domain.scenario.useCase.AddScenarioUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.scenario.useCase.DeleteScenarioUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.scenario.useCase.GetLastScenarioListUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.scenario.useCase.GetScenarioByDocumentNameUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.scenario.useCase.GetScenarioByUrlUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.scenario.useCase.GetScenarioListUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val SCENARIO_DOMAIN_LIB_MODULE = module {

    // Use Case
    singleOf(::AddScenarioUseCase)
    singleOf(::DeleteScenarioUseCase)
    factoryOf(::GetScenarioByUrlUseCase)
    singleOf(::GetScenarioListUseCase)
    singleOf(::GetLastScenarioListUseCase)
    singleOf(::GetScenarioByDocumentNameUseCase)
}
