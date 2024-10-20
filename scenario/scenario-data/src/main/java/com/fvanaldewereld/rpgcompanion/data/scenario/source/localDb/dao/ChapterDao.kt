package com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.dao

import android.database.sqlite.SQLiteException
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.entity.ChapterLocalDb

@Dao
interface ChapterDao {

    @Query("SELECT * FROM scenario_chapter WHERE scenario_id = :scenarioId")
    fun getAllByScenarioId(scenarioId: Long): List<ChapterLocalDb>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    @Throws(SQLiteException::class)
    fun insert(chapterLocalDb: ChapterLocalDb)

    @Transaction
    fun insertAll(chapterLocalDbEntities: List<ChapterLocalDb>) = chapterLocalDbEntities.forEach { insert(it) }

    @Delete
    fun delete(chapterLocalDb: ChapterLocalDb)

}
