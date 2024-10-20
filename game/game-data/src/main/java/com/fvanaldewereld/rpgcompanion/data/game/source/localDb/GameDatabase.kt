package com.fvanaldewereld.rpgcompanion.data.game.source.localDb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fvanaldewereld.rpgcompanion.data.game.source.localDb.dao.GameDao
import com.fvanaldewereld.rpgcompanion.data.game.source.localDb.entity.GameLocalDb

@Database(
    entities = [
        GameLocalDb::class,
    ],
    version = 1,
    exportSchema = true,
)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao

    companion object {
        const val DATABASE_NAME = "game-database"
    }
}
