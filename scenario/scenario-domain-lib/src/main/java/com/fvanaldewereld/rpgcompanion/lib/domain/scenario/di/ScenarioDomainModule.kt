package com.fvanaldewereld.rpgcompanion.lib.domain.scenario.di

import com.fvanaldewereld.rpgcompanion.lib.domain.scenario.usecases.AddScenarioUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.scenario.usecases.DeleteScenarioUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.scenario.usecases.GetLastScenarioListUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.scenario.usecases.GetScenarioByDocumentNameUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.scenario.usecases.GetScenarioByUrlUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.scenario.usecases.GetScenarioListUseCase
import org.koin.dsl.module

val SCENARIO_DOMAIN_LIB_MODULE = module {
    // Usecases
    single { AddScenarioUseCase() }
    single { DeleteScenarioUseCase() }
    factory { GetScenarioByUrlUseCase() }
    single { GetScenarioListUseCase() }
    single { GetLastScenarioListUseCase() }
    single { GetScenarioByDocumentNameUseCase() }
}
