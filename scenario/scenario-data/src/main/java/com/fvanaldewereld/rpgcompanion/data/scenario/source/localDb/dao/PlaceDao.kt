package com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.dao

import android.database.sqlite.SQLiteException
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.entity.PlaceLocalDb

@Dao
interface PlaceDao {

    @Query("SELECT * FROM scenario_place WHERE scenario_id = :scenarioId")
    fun getAllByScenarioId(scenarioId: Long): List<PlaceLocalDb>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    @Throws(SQLiteException::class)
    fun insert(placeLocalDb: PlaceLocalDb)

    @Transaction
    fun insertAll(placeLocalDbList: List<PlaceLocalDb>) = placeLocalDbList.forEach { insert(it) }

    @Delete
    fun delete(placeLocalDb: PlaceLocalDb)
}
