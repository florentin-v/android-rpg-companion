package com.fvanaldewereld.rpgcompanion.data.session.source.localDb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "session")
data class SessionLocalDb(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo("game_id") val gameId: Long? = null,
    val title: String,
    val status: SessionStatus,
)
