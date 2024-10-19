package com.fvanaldewereld.rpgcompanion.data.character.sources.localDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fvanaldewereld.rpgcompanion.data.character.sources.localDatabase.dao.CharacterDao
import com.fvanaldewereld.rpgcompanion.data.character.sources.localDatabase.entities.Character

@Database(
    entities = [
        Character::class,
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
