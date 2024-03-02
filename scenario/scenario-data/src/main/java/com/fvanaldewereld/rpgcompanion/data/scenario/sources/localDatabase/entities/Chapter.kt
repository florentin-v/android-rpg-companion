package com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chapter")
data class Chapter(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    var scenarioId: Long? = null,
    val name: String? = null,
    val description: List<String>? = null,
) : LocalDbEntity
