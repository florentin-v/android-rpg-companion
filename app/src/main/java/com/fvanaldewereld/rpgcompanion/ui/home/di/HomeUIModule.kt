package com.fvanaldewereld.rpgcompanion.ui.home.di

import com.fvanaldewereld.rpgcompanion.ui.home.mapper.LastCharacterUIMapper
import com.fvanaldewereld.rpgcompanion.ui.home.mapper.LastScenarioUIMapper
import com.fvanaldewereld.rpgcompanion.ui.home.viewModel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val HOME_UI_MODULE = module {

    // Mapper
    singleOf(::LastScenarioUIMapper)
    singleOf(::LastCharacterUIMapper)

    // ViewModel
    viewModelOf(::HomeViewModel)
}
