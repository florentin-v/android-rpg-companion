package com.fvanaldewereld.rpgcompanion.data.character.sources.localDatabase.dao

import android.database.sqlite.SQLiteException
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.fvanaldewereld.rpgcompanion.data.character.sources.localDatabase.entities.Character as Character

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character WHERE id = :characterId")
    fun getCharacterById(characterId: Long): Character

    @Query("SELECT * FROM character WHERE game_id = :gameId")
    fun getAllCharacterListByGameId(gameId: Long): List<Character>

    @Query("SELECT * FROM character")
    fun getAllCharacterList(): List<Character>

    @Query("SELECT * FROM character LIMIT :number")
    fun getLastCharacterList(number: Int): List<Character>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    @Throws(SQLiteException::class)
    fun insert(character: Character)

    @Transaction
    fun insertAll(characters: List<Character>) = characters.forEach { insert(it) }

    @Delete
    fun delete(character: Character)
}
