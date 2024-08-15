package com.fvanaldewereld.rpgcompanion.ui.home.di

import com.fvanaldewereld.rpgcompanion.ui.home.mapper.LastScenarioUIMapper
import com.fvanaldewereld.rpgcompanion.ui.home.mapper.LastScenarioUIMapperImpl
import com.fvanaldewereld.rpgcompanion.ui.home.viewModel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val HOME_UI_MODULE = module {

    // Mappers
    single<LastScenarioUIMapper> { LastScenarioUIMapperImpl() }

    // ViewModels
    viewModelOf(::HomeViewModel)
}
