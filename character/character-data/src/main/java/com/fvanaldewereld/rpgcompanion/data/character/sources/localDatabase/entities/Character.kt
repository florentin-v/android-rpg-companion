package com.fvanaldewereld.rpgcompanion.data.character.sources.localDatabase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
data class Character(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo("game_id") val gameId: Long? = null,
    val level: Int,
    val name: String,
) : LocalDbEntity
