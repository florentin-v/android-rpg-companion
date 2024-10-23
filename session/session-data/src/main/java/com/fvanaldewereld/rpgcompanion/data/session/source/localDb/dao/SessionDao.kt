package com.fvanaldewereld.rpgcompanion.data.session.source.localDb.dao

import android.database.sqlite.SQLiteException
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fvanaldewereld.rpgcompanion.data.session.source.localDb.entity.SessionLocalDb

@Dao
interface SessionDao {

    @Query("SELECT * FROM session WHERE id = :sessionId")
    fun getSessionById(sessionId: Long): SessionLocalDb

    @Query("SELECT * FROM session WHERE game_id = :gameId")
    fun getAllSessionListByGameId(gameId: Long): List<SessionLocalDb>

    @Query("SELECT * FROM session")
    fun getAllSessionList(): List<SessionLocalDb>

    @Query("SELECT * FROM session LIMIT :number")
    fun getLastSessionList(number: Int): List<SessionLocalDb>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    @Throws(SQLiteException::class)
    fun insert(sessionLocalDb: SessionLocalDb)

    @Delete
    fun delete(sessionLocalDb: SessionLocalDb)
}
