package com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.dao.ChapterDao
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.dao.CharacterDao
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.dao.PlaceDao
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.dao.ScenarioBaseDao
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.entities.Chapter
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.entities.Character
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.entities.Place
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.entities.ScenarioBase

@Database(
    entities = [
        ScenarioBase::class,
        Chapter::class,
        Character::class,
        Place::class,
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
