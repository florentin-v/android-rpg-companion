package com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.dao

import android.database.sqlite.SQLiteException
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.entity.ScenarioBaseLocalDb

@Dao
interface ScenarioBaseDao {

    @Query("SELECT * FROM scenario_base")
    fun getAllScenarioListBase(): List<ScenarioBaseLocalDb>

    @Query("SELECT * FROM scenario_base LIMIT :number")
    fun getLastScenarioListBase(number: Int): List<ScenarioBaseLocalDb>

    @Query("SELECT * FROM scenario_base WHERE id = :scenarioId")
    fun getScenarioBaseById(scenarioId: Long): ScenarioBaseLocalDb

    @Query("SELECT * FROM scenario_base WHERE document_name LIKE :documentName LIMIT 1")
    fun getScenarioBaseByDocumentName(documentName: String): ScenarioBaseLocalDb

    @Insert(onConflict = OnConflictStrategy.ABORT)
    @Throws(SQLiteException::class)
    fun insertScenarioBase(scenarioBaseLocalDb: ScenarioBaseLocalDb): Long

    @Delete
    fun deleteScenarioBase(scenarioBaseLocalDb: ScenarioBaseLocalDb)
}
