package com.fvanaldewereld.rpgcompanion.data.session.di

import androidx.room.Room
import com.fvanaldewereld.rpgcompanion.api.domain.session.repository.DbSessionRepository
import com.fvanaldewereld.rpgcompanion.data.session.mapper.SessionMapper
import com.fvanaldewereld.rpgcompanion.data.session.mapper.SessionStatusMapper
import com.fvanaldewereld.rpgcompanion.data.session.repository.LocalDbSessionRepository
import com.fvanaldewereld.rpgcompanion.data.session.source.localDb.SessionDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val SESSION_DATA_MODULE = module {

    // Mapper
    singleOf(::SessionMapper)
    singleOf(::SessionStatusMapper)

    // Source
    single<SessionDatabase> {
        Room.databaseBuilder(
            androidContext(),
            SessionDatabase::class.java,
            SessionDatabase.DATABASE_NAME,
        ).build()
    }
    singleOf(SessionDatabase::sessionDao)

    // Repository
    singleOf(::LocalDbSessionRepository) bind DbSessionRepository::class
}
