package com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scenario_place")
data class Place(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo("scenario_id") var scenarioId: Long? = null,
    val name: String,
    val description: List<String>,
) : LocalDbEntity
