package com.fvanaldewereld.rpgcompanion.data.session.source.localDb

import androidx.room.TypeConverter
import com.fvanaldewereld.rpgcompanion.data.session.source.localDb.entity.SessionStatus

object Converters {

    @TypeConverter
    fun toSessionStatus(value: String) = enumValueOf<SessionStatus>(value)

    @TypeConverter
    fun fromSessionStatus(value: SessionStatus) = value.name
}
