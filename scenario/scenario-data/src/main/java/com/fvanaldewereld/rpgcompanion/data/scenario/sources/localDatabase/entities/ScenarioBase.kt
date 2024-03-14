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
    @ColumnInfo(name = "document_name") val documentName: String,
    val author: String? = null,
    @Embedded val information: Information = Information(),
    val summary: List<String> = emptyList(),
    val title: String? = null,
) : LocalDbEntity
