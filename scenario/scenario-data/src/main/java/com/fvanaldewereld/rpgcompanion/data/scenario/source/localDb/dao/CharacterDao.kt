package com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.dao

import android.database.sqlite.SQLiteException
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.entity.CharacterLocalDb

@Dao
interface CharacterDao {

    @Query("SELECT * FROM scenario_character WHERE scenario_id = :scenarioId")
    fun getAllByScenarioId(scenarioId: Long): List<CharacterLocalDb>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    @Throws(SQLiteException::class)
    fun insert(characterLocalDb: CharacterLocalDb)

    @Transaction
    fun insertAll(characterLocalDbList: List<CharacterLocalDb>) = characterLocalDbList.forEach { insert(it) }

    @Delete
    fun delete(characterLocalDb: CharacterLocalDb)
}
