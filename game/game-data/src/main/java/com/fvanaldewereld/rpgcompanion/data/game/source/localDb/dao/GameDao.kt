package com.fvanaldewereld.rpgcompanion.data.game.source.localDb.dao

import android.database.sqlite.SQLiteException
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fvanaldewereld.rpgcompanion.data.game.source.localDb.entity.GameLocalDb

@Dao
interface GameDao {

    @Query("SELECT * FROM game WHERE id = :gameId")
    fun getGameById(gameId: Long): GameLocalDb

    @Query("SELECT * FROM game")
    fun getAllGameList(): List<GameLocalDb>

    @Query("SELECT * FROM game LIMIT :number")
    fun getLastGameList(number: Int): List<GameLocalDb>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    @Throws(SQLiteException::class)
    fun insert(gameLocalDb: GameLocalDb)

    @Delete
    fun delete(gameLocalDb: GameLocalDb)
}
