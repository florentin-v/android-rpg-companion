package com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scenario_character")
data class CharacterLocalDb(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo("scenario_id") var scenarioId: Long? = null,
    val name: String,
    val description: List<String>,
)
