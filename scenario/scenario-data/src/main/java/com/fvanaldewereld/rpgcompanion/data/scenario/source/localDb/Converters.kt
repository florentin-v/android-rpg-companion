package com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object Converters {
    @TypeConverter
    fun toList(value: String) = Json.decodeFromString<List<String>>(value)

    @TypeConverter
    fun fromList(value: List<String?>) = Json.encodeToString(value)
}
