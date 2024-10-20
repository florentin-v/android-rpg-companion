package com.fvanaldewereld.rpgcompanion.data.session.source.localDb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fvanaldewereld.rpgcompanion.data.session.source.localDb.dao.SessionDao
import com.fvanaldewereld.rpgcompanion.data.session.source.localDb.entity.SessionLocalDb

@Database(
    entities = [
        SessionLocalDb::class,
    ],
    version = 1,
    exportSchema = true,
)
@TypeConverters(Converters::class)
abstract class SessionDatabase : RoomDatabase() {
    abstract fun sessionDao(): SessionDao

    companion object {
        const val DATABASE_NAME = "session-database"
    }
}
