package com.fvanaldewereld.rpgcompanion.data.session.source.localDb.dao

import android.database.sqlite.SQLiteException
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SessionDao {

    @Query("SELECT * FROM session WHERE id = :sessionId")
    fun getSessionById(sessionId: Long): com.fvanaldewereld.rpgcompanion.data.session.source.localDb.entity.SessionLocalDb

    @Query("SELECT * FROM session WHERE game_id = :gameId")
    fun getAllSessionListByGameId(gameId: Long): List<com.fvanaldewereld.rpgcompanion.data.session.source.localDb.entity.SessionLocalDb>

    @Query("SELECT * FROM session")
    fun getAllSessionList(): List<com.fvanaldewereld.rpgcompanion.data.session.source.localDb.entity.SessionLocalDb>

    @Query("SELECT * FROM session LIMIT :number")
    fun getLastSessionList(number: Int): List<com.fvanaldewereld.rpgcompanion.data.session.source.localDb.entity.SessionLocalDb>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    @Throws(SQLiteException::class)
    fun insert(sessionLocalDb: com.fvanaldewereld.rpgcompanion.data.session.source.localDb.entity.SessionLocalDb)

    @Delete
    fun delete(sessionLocalDb: com.fvanaldewereld.rpgcompanion.data.session.source.localDb.entity.SessionLocalDb)
}
