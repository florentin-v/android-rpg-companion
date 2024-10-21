package com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.dao.ChapterDao
import com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.dao.CharacterDao
import com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.dao.PlaceDao
import com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.dao.ScenarioBaseDao
import com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.entity.ChapterLocalDb
import com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.entity.CharacterLocalDb
import com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.entity.PlaceLocalDb
import com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.entity.ScenarioBaseLocalDb

@Database(
    entities = [
        ScenarioBaseLocalDb::class,
        ChapterLocalDb::class,
        CharacterLocalDb::class,
        PlaceLocalDb::class,
    ],
    version = 1,
    exportSchema = true,
)
@TypeConverters(Converters::class)
abstract class ScenarioDatabase : RoomDatabase() {
    abstract fun scenarioBaseDao(): ScenarioBaseDao
    abstract fun chapterDao(): ChapterDao
    abstract fun characterDao(): CharacterDao
    abstract fun placeDao(): PlaceDao

    companion object {
        const val DATABASE_NAME = "scenario-database"
    }
}
