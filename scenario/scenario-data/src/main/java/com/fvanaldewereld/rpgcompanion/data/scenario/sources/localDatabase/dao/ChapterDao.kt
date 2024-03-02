package com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.dao

import android.database.sqlite.SQLiteException
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.entities.Chapter

@Dao
interface ChapterDao {

    @Query("SELECT * FROM chapter WHERE scenarioId = :scenarioId")
    fun getAllByScenarioId(scenarioId: Long): List<Chapter>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    @Throws(SQLiteException::class)
    fun insert(chapter: Chapter)

    @Transaction
    fun insertAll(chapters: List<Chapter>) = chapters.forEach { insert(it) }

    @Delete
    fun delete(chapter: Chapter)
}
