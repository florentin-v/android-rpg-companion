package com.fvanaldewereld.rpgcompanion.ui.scenario.list.di

import com.fvanaldewereld.rpgcompanion.ui.scenario.list.ScenarioListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val SCENARIO_UI_LIST_MODULE = module {
    // ViewModels
    viewModelOf(::ScenarioListViewModel)
}
