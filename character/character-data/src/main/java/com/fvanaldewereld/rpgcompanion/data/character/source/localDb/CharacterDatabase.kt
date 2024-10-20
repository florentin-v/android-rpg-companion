package com.fvanaldewereld.rpgcompanion.data.character.source.localDb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fvanaldewereld.rpgcompanion.data.character.source.localDb.dao.CharacterDao
import com.fvanaldewereld.rpgcompanion.data.character.source.localDb.entity.CharacterLocalDb

@Database(
    entities = [
        CharacterLocalDb::class,
    ],
    version = 1,
    exportSchema = true,
)
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao

    companion object {
        const val DATABASE_NAME = "character-database"
    }
}
