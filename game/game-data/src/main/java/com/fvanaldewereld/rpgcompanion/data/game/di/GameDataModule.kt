package com.fvanaldewereld.rpgcompanion.data.game.di

import androidx.room.Room
import com.fvanaldewereld.rpgcompanion.api.domain.game.repository.DbGameRepository
import com.fvanaldewereld.rpgcompanion.data.game.mapper.GameMapper
import com.fvanaldewereld.rpgcompanion.data.game.repository.LocalDbGameRepository
import com.fvanaldewereld.rpgcompanion.data.game.source.localDb.GameDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val GAME_DATA_MODULE = module {

    // Mapper
    singleOf(::GameMapper)

    // Source
    single<GameDatabase> {
        Room.databaseBuilder(
            androidContext(),
            GameDatabase::class.java,
            GameDatabase.Companion.DATABASE_NAME,
        ).build()
    }
    singleOf(GameDatabase::gameDao)

    // Repository
    singleOf(::LocalDbGameRepository) bind DbGameRepository::class
}
