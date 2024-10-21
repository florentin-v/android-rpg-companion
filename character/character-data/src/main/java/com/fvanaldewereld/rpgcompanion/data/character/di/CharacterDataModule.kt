package com.fvanaldewereld.rpgcompanion.data.character.di

import androidx.room.Room
import com.fvanaldewereld.rpgcompanion.api.domain.character.repository.DbCharacterRepository
import com.fvanaldewereld.rpgcompanion.data.character.mapper.CharacterMapper
import com.fvanaldewereld.rpgcompanion.data.character.repository.LocalDbCharacterRepository
import com.fvanaldewereld.rpgcompanion.data.character.source.localDb.CharacterDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val CHARACTER_DATA_MODULE = module {

    // Mapper
    singleOf(::CharacterMapper)

    // Source
    single<CharacterDatabase> {
        Room.databaseBuilder(
            androidContext(),
            CharacterDatabase::class.java,
            CharacterDatabase.DATABASE_NAME,
        ).build()
    }
    singleOf(CharacterDatabase::characterDao)

    // Repository
    singleOf(::LocalDbCharacterRepository) bind DbCharacterRepository::class
}
