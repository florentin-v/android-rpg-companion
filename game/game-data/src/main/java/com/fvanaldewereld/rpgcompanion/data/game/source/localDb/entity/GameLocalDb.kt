package com.fvanaldewereld.rpgcompanion.data.game.source.localDb.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game")
data class GameLocalDb(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
)
