package com.fvanaldewereld.rpgcompanion.data.session.source.localDb

import androidx.room.TypeConverter

object Converters {

    @TypeConverter
    fun toSessionStatus(value: String) = enumValueOf<com.fvanaldewereld.rpgcompanion.data.session.source.localDb.entity.SessionStatus>(value)

    @TypeConverter
    fun fromSessionStatus(value: com.fvanaldewereld.rpgcompanion.data.session.source.localDb.entity.SessionStatus) = value.name
}
