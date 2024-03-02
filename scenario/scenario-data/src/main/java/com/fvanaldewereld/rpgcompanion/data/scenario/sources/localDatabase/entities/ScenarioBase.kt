package com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.embedded.Information

@Entity(
    tableName = "scenario_base",
    indices = [
        Index(
            value = ["document_name"],
            unique = true,
        ),
    ],
)
data class ScenarioBase(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "document_name") val documentName: String? = null,
    val author: String? = null,
    @Embedded val information: Information? = null,
    val summary: List<String>? = null,
    val title: String? = null,
) : LocalDbEntity
