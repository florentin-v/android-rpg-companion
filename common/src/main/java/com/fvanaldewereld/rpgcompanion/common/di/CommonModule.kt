package com.fvanaldewereld.rpgcompanion.common.di

import com.fvanaldewereld.rpgcompanion.common.dispatchers.KDispatchers
import com.fvanaldewereld.rpgcompanion.common.dispatchers.KDispatchersImpl
import org.koin.dsl.module

val COMMON_MODULE = module {
    // Dispatchers
    single<KDispatchers> { KDispatchersImpl() }
}
