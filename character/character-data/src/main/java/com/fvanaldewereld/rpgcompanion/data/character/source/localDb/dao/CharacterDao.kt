package com.fvanaldewereld.rpgcompanion.data.character.source.localDb.dao

import android.database.sqlite.SQLiteException
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fvanaldewereld.rpgcompanion.data.character.source.localDb.entity.CharacterLocalDb

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character WHERE id = :characterId")
    fun getCharacterById(characterId: Long): CharacterLocalDb

    @Query("SELECT * FROM character WHERE game_id = :gameId")
    fun getAllCharacterListByGameId(gameId: Long): List<CharacterLocalDb>

    @Query("SELECT * FROM character")
    fun getAllCharacterList(): List<CharacterLocalDb>

    @Query("SELECT * FROM character LIMIT :number")
    fun getLastCharacterList(number: Int): List<CharacterLocalDb>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    @Throws(SQLiteException::class)
    fun insert(characterLocalDb: CharacterLocalDb)

    @Delete
    fun delete(characterLocalDb: CharacterLocalDb)
}
