package com.fvanaldewereld.rpgcompanion.data.character.source.localDb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
data class CharacterLocalDb(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo("game_id") val gameId: Long? = null,
    val level: Int,
    val name: String,
)
