package com.fvanaldewereld.rpgcompanion.ui.scenario.list.di

import androidx.lifecycle.SavedStateHandle
import com.fvanaldewereld.rpgcompanion.ui.scenario.list.ScenarioListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val SCENARIO_UI_LIST_MODULE = module {
    // ViewModels
    viewModel { (savedStateHandle: SavedStateHandle) -> ScenarioListViewModel(savedStateHandle) }
}
