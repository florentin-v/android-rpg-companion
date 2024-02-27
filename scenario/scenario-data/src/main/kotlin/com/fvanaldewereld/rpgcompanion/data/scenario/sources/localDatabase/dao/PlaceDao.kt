package com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.dao

import android.database.sqlite.SQLiteException
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.entities.Place

@Dao
interface PlaceDao {

    @Query("SELECT * FROM place WHERE scenarioId = :scenarioId")
    fun getAllByScenarioId(scenarioId: Long): List<Place>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    @Throws(SQLiteException::class)
    fun insert(place: Place)

    @Transaction
    fun insertAll(places: List<Place>) = places.forEach { insert(it) }

    @Delete
    fun delete(place: Place)
}
