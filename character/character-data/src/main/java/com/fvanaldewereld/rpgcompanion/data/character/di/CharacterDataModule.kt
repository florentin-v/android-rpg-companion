package com.fvanaldewereld.rpgcompanion.data.character.di

import androidx.room.Room
import com.fvanaldewereld.rpgcompanion.api.domain.character.repositories.DbCharacterRepository
import com.fvanaldewereld.rpgcompanion.data.character.mappers.CharacterMapper
import com.fvanaldewereld.rpgcompanion.data.character.mappers.CharacterMapperImpl
import com.fvanaldewereld.rpgcompanion.data.character.repositories.LocalDbCharacterRepositoryImpl
import com.fvanaldewereld.rpgcompanion.data.character.sources.localDatabase.CharacterDatabase
import com.fvanaldewereld.rpgcompanion.data.character.sources.localDatabase.dao.CharacterDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val CHARACTER_DATA_MODULE = module {

    // DbObjectMapper
    single<CharacterMapper> { CharacterMapperImpl() }

    // Sources
    single<CharacterDatabase> {
        Room.databaseBuilder(
            androidContext(),
            CharacterDatabase::class.java,
            CharacterDatabase.DATABASE_NAME,
        ).build()
    }
    single<CharacterDao> { get<CharacterDatabase>().characterDao() }

    // Repositories
    single<DbCharacterRepository> { LocalDbCharacterRepositoryImpl() }
}
