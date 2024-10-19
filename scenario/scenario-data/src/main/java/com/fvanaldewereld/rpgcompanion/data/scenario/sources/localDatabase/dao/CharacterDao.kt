package com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.dao

import android.database.sqlite.SQLiteException
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.entities.Character

@Dao
interface CharacterDao {

    @Query("SELECT * FROM scenario_character WHERE scenario_id = :scenarioId")
    fun getAllByScenarioId(scenarioId: Long): List<Character>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    @Throws(SQLiteException::class)
    fun insert(character: Character)

    @Transaction
    fun insertAll(characters: List<Character>) = characters.forEach { insert(it) }

    @Delete
    fun delete(character: Character)
}
