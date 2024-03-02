package com.fvanaldewereld.rpgcompanion.ui.scenario.detail.di

import com.fvanaldewereld.rpgcompanion.ui.scenario.detail.ScenarioDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val SCENARIO_UI_DETAIL_MODULE = module {
    // ViewModels
    viewModelOf(::ScenarioDetailViewModel)
}
