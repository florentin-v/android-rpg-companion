package com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.dao

import android.database.sqlite.SQLiteException
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.entities.ScenarioBase

@Dao
interface ScenarioBaseDao {

    @Query("SELECT * FROM scenario_base")
    fun getAllScenariosBase(): List<ScenarioBase>

    @Query("SELECT * FROM scenario_base LIMIT :number")
    fun getLastScenariosBase(number: Int): List<ScenarioBase>

    @Query("SELECT * FROM scenario_base WHERE id = :scenarioId")
    fun getScenarioBaseById(scenarioId: Long): ScenarioBase

    @Query("SELECT * FROM scenario_base WHERE document_name LIKE :documentName LIMIT 1")
    fun getScenarioBaseByDocumentName(documentName: String): ScenarioBase

    @Insert(onConflict = OnConflictStrategy.ABORT)
    @Throws(SQLiteException::class)
    fun insertScenarioBase(scenarioBase: ScenarioBase): Long

    @Delete
    fun deleteScenarioBase(scenarioBase: ScenarioBase)
}
