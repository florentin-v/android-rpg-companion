package com.fvanaldewereld.rpgcompanion.ui.home.di

import com.fvanaldewereld.rpgcompanion.ui.home.mapper.CharacterUIMapper
import com.fvanaldewereld.rpgcompanion.ui.home.mapper.LastScenarioUIMapper
import com.fvanaldewereld.rpgcompanion.ui.home.viewModel.HomeViewModel
import com.fvanaldewereld.rpgcompanion.ui.home.viewModel.slice.GetLastCharacterListSlice
import com.fvanaldewereld.rpgcompanion.ui.home.viewModel.slice.GetLastCharacterListSliceImpl
import com.fvanaldewereld.rpgcompanion.ui.home.viewModel.slice.GetLastGameListSlice
import com.fvanaldewereld.rpgcompanion.ui.home.viewModel.slice.GetLastGameListSliceImpl
import com.fvanaldewereld.rpgcompanion.ui.home.viewModel.slice.GetLastScenarioListSlice
import com.fvanaldewereld.rpgcompanion.ui.home.viewModel.slice.GetLastScenarioListSliceImpl
import com.fvanaldewereld.rpgcompanion.ui.home.viewModel.slice.GetLastSessionListSlice
import com.fvanaldewereld.rpgcompanion.ui.home.viewModel.slice.GetLastSessionListSliceImpl
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val HOME_UI_MODULE = module {

    // Mapper
    singleOf(::LastScenarioUIMapper)
    singleOf(::CharacterUIMapper)

    // ViewModel
    viewModelOf(::HomeViewModel)

    // Slice
    factoryOf(::GetLastCharacterListSliceImpl) bind GetLastCharacterListSlice::class
    factoryOf(::GetLastGameListSliceImpl) bind GetLastGameListSlice::class
    factoryOf(::GetLastScenarioListSliceImpl) bind GetLastScenarioListSlice::class
    factoryOf(::GetLastSessionListSliceImpl) bind GetLastSessionListSlice::class

}
